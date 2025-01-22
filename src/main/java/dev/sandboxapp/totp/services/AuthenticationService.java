package dev.sandboxapp.totp.services;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@AllArgsConstructor
@Service
public class AuthenticationService {

  private final UserRepository userRepo;
  private final TotpService totpService;
  private final JwtTokenService jwtTokenService;
  private final int TIME_PERIOD = 10 * 60; // 10 minutes

  public User getUser(String emailOrPhoneNumber) {
    return userRepo
      .findByEmailOrPhoneNumber(emailOrPhoneNumber)
      .orElseThrow(() -> new EntityNotFoundException("User not found: " + emailOrPhoneNumber));
  }

  public String generateSignInOtp(String emailOrPhoneNumber) throws CodeGenerationException {
    var user = getUser(emailOrPhoneNumber);
    return this.totpService.generateSignInOtp(user.getId().toString(), TIME_PERIOD);
  }

  public String verifySignInOtp(String emailOrPhoneNumber, String otp) throws Exception {
    var user = getUser(emailOrPhoneNumber);
    if (this.totpService.verifySignInOtp(user.getId().toString(), otp, TIME_PERIOD)) {
      return jwtTokenService.generateToken(emailOrPhoneNumber);
    }
    throw new Exception("Invalid sign in otp");
  }
}
