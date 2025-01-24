package dev.sandboxapp.totp.utils;

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

  public static String generateSignupValidationToken(String token) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] hashBytes = digest.digest(token.getBytes(StandardCharsets.UTF_8));
    char[] hashHex = Hex.encode(hashBytes);
    return String.valueOf(hashHex);
  }

  public static boolean verifySignupValidationToken(String userId, String token) throws NoSuchAlgorithmException{
    return generateSignupValidationToken(userId).equals(token);
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
}
