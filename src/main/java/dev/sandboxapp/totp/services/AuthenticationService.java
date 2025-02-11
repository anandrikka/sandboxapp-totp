package dev.sandboxapp.totp.services;

import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.sandboxapp.totp.dto.requests.SignupRequest;
import dev.sandboxapp.totp.exceptions.ExceptionUtils;
import dev.sandboxapp.totp.models.ActivationToken;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.ActivationTokenRepository;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.utils.AuthUtils;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

  private final UserRepository userRepo;
  private final TotpService totpService;
  private final JwtTokenService jwtTokenService;
  private final ActivationTokenRepository activationTokenRepo;
  private final int TIME_PERIOD = 10 * 60; // 10 minutes
  private final EmailService emailService;

  @Value("${host.identifier}")
  private String host;

  @Transactional
  public void createAccount(SignupRequest request) throws MessagingException {
    var user = new User();
    user.setFirstName(request.firstName());
    user.setLastName(request.lastName());
    user.setEmail(request.email());
    var activationToken = new ActivationToken();
    activationToken.setUser(user);
    activationToken.setToken(AuthUtils.randomToken());
    activationToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
    user.getActivationTokens().add(activationToken);
    userRepo.save(user);
    sendActivationEmail(user, activationToken.getToken());
  }

  public void verifySignupRequest(String email, String token) {
    var user = userRepo.findByEmail(email).orElseThrow(() -> ExceptionUtils.userNotFound(email));
    var activationToken = activationTokenRepo.findByUserIdAndToken(user.getId(), token)
      .orElseThrow(() -> ExceptionUtils.tokenNotFound(email, token));
    if (activationToken.getUsedAt() != null) {
      ExceptionUtils.raiseTokenUsed(email, token);
    }
    if (activationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
      ExceptionUtils.raiseTokenExpired(email, token);
    }
    user.setActivated(true);
    userRepo.save(user);
    activationToken.setUsedAt(LocalDateTime.now());
    activationTokenRepo.save(activationToken);
  }

  @Transactional
  public void resendVerificationLink(String email) throws MessagingException {
    var user = userRepo.findByEmail(email).orElseThrow(() -> ExceptionUtils.userNotFound(email));
    activationTokenRepo.markAllTokensAsUsed(user.getId(), LocalDateTime.now());
    var newToken = new ActivationToken();
    newToken.setExpiresAt(LocalDateTime.now().plusMinutes(60));
    newToken.setToken(AuthUtils.randomToken());
    newToken.setUser(user);
    activationTokenRepo.save(newToken);
    sendActivationEmail(user, newToken.getToken());
  }

  private void sendActivationEmail(User user, String token) throws MessagingException {
    var emailInputs = new HashMap<String, Object>();
    emailInputs.put("activationCode", token);
    emailInputs.put("user", user);
    emailInputs.put("requestHost", host);
    var emailBody = emailService.generateEmailBody("activation-email", emailInputs);
    emailService.sendEmail(user.getEmail(), "Authy Demo: Activate your account", emailBody);
  }

  public User getUser(String emailOrPhoneNumber) {
    return userRepo
      .findByEmail(emailOrPhoneNumber)
      .orElseThrow(() -> new EntityNotFoundException("User not found: " + emailOrPhoneNumber));
  }

  public String generateSignInOtp(String emailOrPhoneNumber) throws CodeGenerationException {
    var user = getUser(emailOrPhoneNumber);
    return this.totpService.generateSignInOtp(user.usableId(), TIME_PERIOD);
  }

  public String verifySignInOtp(String emailOrPhoneNumber, String otp) throws Exception {
    var user = getUser(emailOrPhoneNumber);
    if (this.totpService.verifySignInOtp(user.usableId(), otp, TIME_PERIOD)) {
      return jwtTokenService.generateToken(user);
    }
    throw new Exception("Invalid sign in otp");
  }
}
