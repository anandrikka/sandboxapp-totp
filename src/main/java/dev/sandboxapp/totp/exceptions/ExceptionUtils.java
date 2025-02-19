package dev.sandboxapp.totp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ExceptionUtils {

  public static GlobalException userNotFound(String email) {
    return new GlobalException(builder -> builder
      .code("user.not_found")
      .status(HttpStatus.NOT_FOUND)
      .message("User not found.")
      .meta("email", email)
    );
  }

  public static GlobalException tokenNotFound(String email, String token) {
    return new GlobalException(builder -> builder
      .code("token.not_found")
      .status(HttpStatus.NOT_FOUND)
      .message("Token is invalid")
      .meta("email", email)
      .meta("token", token)
    );
  }

  public static GlobalException raiseTokenUsed(String email, String token) {
    throw new GlobalException(builder -> builder
      .code("token.used")
      .status(HttpStatus.CONFLICT)
      .message("Token already used.")
      .meta("email", email)
      .meta("token", token)
    );
  }

  public static GlobalException raiseTokenExpired(String email, String token) {
    throw new GlobalException(builder -> builder
      .code("token.expired")
      .status(HttpStatus.BAD_REQUEST)
      .message("Token expired.")
      .meta("email", email)
      .meta("token", token)
    );
  }

  public static void raiseRefreshFailed() {
    throw new GlobalException(builder -> builder
      .code("token.refresh_failed")
      .status(HttpStatus.UNAUTHORIZED)
      .message("Unauthorized User.")
    );
  }

  public static GlobalException accountNotFound(UUID id) {
    return new GlobalException(builder -> builder
      .status(HttpStatus.NOT_FOUND)
      .message("Account not found")
      .code("account.not_found")
    );
  }
}
