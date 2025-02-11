package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.Email;

public record SignupRequest (
  String firstName,
  String lastName,

  @Email(message = "Invalid email address")
  String email
) {}
