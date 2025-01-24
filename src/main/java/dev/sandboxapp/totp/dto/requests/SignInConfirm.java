package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record SignInConfirm(
  @NotEmpty(message = "Email cannot be empty")
  @NotNull(message = "Email cannot be null")
  String email,

  @NotEmpty(message = "Device token cannot be empty")
  @NotNull(message = "Device token cannot be null")
  String deviceToken,

  @NotEmpty(message = "Auth token cannot be empty")
  @NotNull(message = "Auth token cannot be null")
  String authToken,

  Boolean rememberMe,

  @NotNull(message = "Fingerprint cannot be null")
  @NotNull(message = "Fingerprint cannot be empty")
  Map<String, String> fingerprint
) {}
