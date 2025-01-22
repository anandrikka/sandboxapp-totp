package dev.sandboxapp.totp.services;

import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TotpService {
  private final TimeProvider timeProvider;
  private final DefaultCodeVerifier codeVerifier;
  private final DefaultCodeGenerator codeGenerator;
  private final int DEFAULT_TIME_PERIOD = 30;

  public String generateSignInOtp(String secret) throws CodeGenerationException {
    return generateSignInOtp(secret, DEFAULT_TIME_PERIOD);
  }

  public String generateSignInOtp(String secret, int timePeriod) throws CodeGenerationException {
    long currentTimeSeconds = timeProvider.getTime();
    long counter = currentTimeSeconds / timePeriod;
    return this.codeGenerator.generate(secret.toString(), counter);
  }

  public boolean verifySignInOtp(String secret, String otp, int timePeriod) {
    this.codeVerifier.setTimePeriod(timePeriod);
    this.codeVerifier.setAllowedTimePeriodDiscrepancy(1);
    return this.codeVerifier.isValidCode(secret, otp);
  }

  public boolean verifySignInOtp(String secret, String otp) {
    return verifySignInOtp(secret, otp, DEFAULT_TIME_PERIOD);
  }
}
