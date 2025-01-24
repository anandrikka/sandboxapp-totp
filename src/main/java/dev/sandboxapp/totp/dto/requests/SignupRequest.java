package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SignupRequest (
  @NotNull(message = "First name is required")
  String firstName,

  @NotNull(message = "Last name is required")
  String lastName,

  @NotNull(message = "Email is required")
  @Email(message = "Invalid email address")
  String email
) {}
