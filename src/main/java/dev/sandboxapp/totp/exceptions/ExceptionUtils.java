package dev.sandboxapp.totp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ExceptionUtils {

  public static GlobalException userNotFound(String email, String code) {
    return new GlobalException(builder -> builder
      .code(code)
      .status(HttpStatus.NOT_FOUND)
      .message("User not found.")
      .meta("email", email)
    );
  }

  public static GlobalException tokenNotFound(String email, String token, String code) {
    return new GlobalException(builder -> builder
      .code(code)
      .status(HttpStatus.NOT_FOUND)
      .message("Token is invalid")
      .meta("email", email)
      .meta("token", token)
    );
  }

  public static GlobalException raiseActivationTokenInvalid(String email, String token) {
    throw new GlobalException(builder -> builder
      .code("auth.signup.verify.tokenAlreadyUsed")
      .status(HttpStatus.CONFLICT)
      .message("Token already used.")
      .meta("email", email)
      .meta("token", token)
    );
  }

  public static GlobalException emailRegistered(String email) {
    throw new GlobalException(builder -> builder
      .code("auth.signup.emailRegistered")
      .status(HttpStatus.BAD_REQUEST)
      .message("Account already exists.")
      .meta("email", email)
    );
  }

  public static GlobalException raiseTokenExpired(String email, String token, String code) {
    throw new GlobalException(builder -> builder
      .code(code)
      .status(HttpStatus.BAD_REQUEST)
      .message("Token expired.")
      .meta("email", email)
      .meta("token", token)
    );
  }

  public static void raiseRefreshFailed() {
    throw new GlobalException(builder -> builder
      .code("auth.token.refreshFailed")
      .status(HttpStatus.UNAUTHORIZED)
      .message("Unauthorized User.")
    );
  }

  public static GlobalException accountNotFound(UUID id) {
    return new GlobalException(builder -> builder
      .status(HttpStatus.NOT_FOUND)
      .message("Account not found")
      .code("account.notFound")
    );
  }
}
