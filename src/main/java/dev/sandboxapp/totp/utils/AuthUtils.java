package dev.sandboxapp.totp.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AuthUtils {

  public static UUID loggedInUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    return UUID.fromString(principal.toString());
  }

  public static String generateLoginToken() {
    String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var random = new SecureRandom();
    return IntStream.range(0, 6)
      .mapToObj(i -> String.valueOf(alphabets.charAt(random.nextInt(26))))
      .collect(Collectors.joining());
  }

  public static String randomToken() {
    SecureRandom secureRandom = new SecureRandom();
    byte[] randomBytes = new byte[64];
    secureRandom.nextBytes(randomBytes);
    StringBuilder result = new StringBuilder();
    String charSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (byte b : randomBytes) {
      int index = b & 0xFF;
      result.append(charSet.charAt(index % charSet.length()));
    }
    return result.toString();
  }

  public static String getDeviceToken(HttpServletRequest request) {
    return request.getHeader("X-VISITOR-ID");
  }

  public static String getCookie(String name, HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (name.equals(cookie.getName())) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }
}
