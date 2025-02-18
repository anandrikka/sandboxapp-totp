package dev.sandboxapp.totp.services;

import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.sandboxapp.totp.dto.requests.SignInVerificationRequest;
import dev.sandboxapp.totp.dto.requests.SignupRequest;
import dev.sandboxapp.totp.exceptions.ExceptionUtils;
import dev.sandboxapp.totp.models.AccessToken;
import dev.sandboxapp.totp.models.ActivationToken;
import dev.sandboxapp.totp.models.Device;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.AccessTokenRepository;
import dev.sandboxapp.totp.repositories.ActivationTokenRepository;
import dev.sandboxapp.totp.repositories.DeviceRepository;
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
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

  private final UserRepository userRepo;
  private final TotpService totpService;
  private final JwtTokenService jwtTokenService;
  private final ActivationTokenRepository activationTokenRepo;
  private final int TIME_PERIOD = 10 * 60; // 10 minutes
  private final EmailService emailService;
  private final DeviceRepository deviceRepo;
  private final AccessTokenRepository accessTokenRepo;

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

  @Transactional
  public void sendSignInToken(String email, String deviceToken) throws MessagingException {
    var user = userRepo.findByEmail(email).orElseThrow(() -> ExceptionUtils.userNotFound(email));
    var token = AuthUtils.generateLoginToken();
    accessTokenRepo.markAllTokensAsUsed(user.getId(), LocalDateTime.now());
    var accessToken = AccessToken.builder()
      .authToken(token)
      .expiresAt(LocalDateTime.now().plusMinutes(10))
      .deviceToken(deviceToken)
      .user(user)
      .build();
    accessTokenRepo.save(accessToken);
    sendLoginOTP(user, token);
  }

  @Transactional
  public Device verifySignInToken(SignInVerificationRequest request, String deviceName, String deviceToken) {
    var user = userRepo.findByEmail(request.email()).orElseThrow(() -> ExceptionUtils.userNotFound(request.email()));
    var accessToken = accessTokenRepo.findValidAccessToken(user.getId(), deviceToken, request.otp())
      .orElseThrow(() -> ExceptionUtils.tokenNotFound(request.email(), request.otp()));
    if (LocalDateTime.now().isAfter(accessToken.getExpiresAt())) {
      ExceptionUtils.raiseTokenExpired(request.email(), request.otp());
    }
    var device = deviceRepo.findByUserIdAndDeviceToken(user.getId(), deviceToken).orElse(
      Device.builder()
        .user(user)
        .deviceName(deviceName)
        .deviceToken(deviceToken)
        .build()
    );
    device.setRememberMe(request.rememberMe());
    device.setRememberUntil(request.rememberMe() ? LocalDateTime.now().plusDays(30) : null);
    accessToken.setUsedAt(LocalDateTime.now());
    accessTokenRepo.save(accessToken);
    deviceRepo.save(device);
    return device;
  }

  public User verifyDeviceForRefreshToken(String deviceId, String deviceToken) {
    if (deviceId == null) {
      ExceptionUtils.raiseRefreshFailed();
    }
    var device = deviceRepo.findById(UUID.fromString(deviceId));
    if (device.isEmpty()) {
      ExceptionUtils.raiseRefreshFailed();
    }
    if (!device.get().getDeviceToken().equals(deviceToken)) {
      ExceptionUtils.raiseRefreshFailed();
    }
    if (!device.get().isRememberMe() || device.get().getRememberUntil().isBefore(LocalDateTime.now())) {
      ExceptionUtils.raiseRefreshFailed();
    }
    return device.get().getUser();
  }

  private void sendActivationEmail(User user, String token) throws MessagingException {
    var emailInputs = new HashMap<String, Object>();
    emailInputs.put("activationCode", token);
    emailInputs.put("user", user);
    emailInputs.put("requestHost", host);
    var emailBody = emailService.generateEmailBody("activation-email", emailInputs);
    emailService.sendEmail(user.getEmail(), "Authy Demo: Activate your account", emailBody);
  }

  private void sendLoginOTP(User user, String token) throws MessagingException {
    var emailInputs = new HashMap<String, Object>();
    emailInputs.put("loginToken", token);
    emailInputs.put("user", user);
    var emailBody = emailService.generateEmailBody("one-time-password", emailInputs);
    emailService.sendEmail(user.getEmail(), "Authy Demo: OTP to login into your account", emailBody);
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
