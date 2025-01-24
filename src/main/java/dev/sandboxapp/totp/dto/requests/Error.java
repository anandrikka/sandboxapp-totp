package dev.sandboxapp.totp.dto.requests;

import org.springframework.http.HttpStatusCode;

public record Error(
  String code,
  String detail,
  HttpStatusCode status,
  String title
) {}
