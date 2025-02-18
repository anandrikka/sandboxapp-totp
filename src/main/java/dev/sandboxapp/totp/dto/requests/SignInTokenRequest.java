package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record SignInTokenRequest(
  @NotEmpty(message = "Email cannot be empty")
  @NotNull(message = "Email cannot be null")
  String email
) {}
