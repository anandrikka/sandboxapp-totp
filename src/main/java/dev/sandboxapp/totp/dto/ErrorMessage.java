package dev.sandboxapp.totp.dto;

import java.time.LocalDateTime;

public record ErrorMessage(
  String code,
  String message,
  String description,
  LocalDateTime timestamp
) {}
