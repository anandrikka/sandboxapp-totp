package dev.sandboxapp.totp.dto;

public record OneTimePasscode(
  Long counter,
  String code
) {
}
