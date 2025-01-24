package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SignInRequest(
  @NotEmpty(message = "Email cannot be empty")
  @NotNull(message = "Email cannot be null")
  String email,

  @NotEmpty(message = "Device token cannot be empty")
  @NotNull(message = "Device token cannot be null")
  String deviceToken
) {}
