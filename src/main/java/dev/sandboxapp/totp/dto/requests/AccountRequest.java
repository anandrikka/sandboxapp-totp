package dev.sandboxapp.totp.dto.requests;

public record AccountRequest(
  String issuer,
  String secret,
  String name,
  String digits,
  String period
) {}
