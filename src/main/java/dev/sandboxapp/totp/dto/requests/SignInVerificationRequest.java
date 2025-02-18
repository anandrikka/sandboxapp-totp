package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SignInVerificationRequest(
  @NotEmpty(message = "Email cannot be empty")
  @NotNull(message = "Email cannot be null")
  String email,

  @NotEmpty(message = "OTP cannot be empty")
  String otp,
  boolean rememberMe
) {
}
