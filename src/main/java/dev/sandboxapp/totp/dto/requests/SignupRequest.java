package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record SignupRequest (
  @NotEmpty(message = "First name cannot be empty.")
  String firstName,

  @NotEmpty(message = "Last name cannot be empty.")
  String lastName,

  @Email(message = "Invalid email address.")
  @NotEmpty(message = "Email cannot be empty.")
  String email
) {}
