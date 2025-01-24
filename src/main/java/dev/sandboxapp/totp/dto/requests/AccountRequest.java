package dev.sandboxapp.totp.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(
  String issuer,
  @NotNull
  @NotEmpty
  String secret,
  String name,
  String digits,
  String period
) {}
