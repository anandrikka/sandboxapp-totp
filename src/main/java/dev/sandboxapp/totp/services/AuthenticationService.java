package dev.sandboxapp.totp.services;

import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AuthenticationService {

  private final UserRepository userRepo;

  AuthenticationService(
    UserRepository userRepo
  ) {
    this.userRepo = userRepo;
  }

  public User getUser(String emailOrPhoneNumber) {
    return userRepo
      .findByEmailOrPhoneNumber(emailOrPhoneNumber)
      .orElseThrow(() -> new EntityNotFoundException("User not found: " + emailOrPhoneNumber));
  }

  public String generateSignInOtp(String emailOrPhoneNumber) throws CodeGenerationException {
    var user = getUser(emailOrPhoneNumber);
    var timeProvider = new SystemTimeProvider();
    long currentTimeSeconds = timeProvider.getTime();
    long counter = currentTimeSeconds / 30;
    var codeGenerator = new DefaultCodeGenerator();
    return codeGenerator.generate(user.getId().toString(), counter);
  }

  public boolean verifySignInOtp(String emailOrPhoneNumber, String otp) {
    var user = getUser(emailOrPhoneNumber);
    var timeProvider = new SystemTimeProvider();
    var codeGenerator = new DefaultCodeGenerator();
    DefaultCodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);
    verifier.setTimePeriod(30);
    verifier.setAllowedTimePeriodDiscrepancy(1);
    return verifier.isValidCode(user.getId().toString(), otp);
  }
}
