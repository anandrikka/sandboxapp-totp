package dev.sandboxapp.totp.dto.requests;

public record SignInRequest(String emailOrPhoneNumber, String code) {}
