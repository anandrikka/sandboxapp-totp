package dev.sandboxapp.totp.dto;

import org.springframework.http.HttpStatus;

import java.util.Map;

public record Error(
  String code,
  String detail,
  String title,
  HttpStatus status,
  Map<String, String> meta
) {}
