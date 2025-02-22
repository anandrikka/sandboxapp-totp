package dev.sandboxapp.totp.exceptions.handlers;

import dev.sandboxapp.totp.dto.ErrorRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler {

  @ExceptionHandler({ AuthenticationException.class })
  ResponseEntity<ErrorRecord> unauthourizedException(AuthenticationException ex) {
    var error = ErrorRecord.builder()
      .message(ex.getMessage())
      .status(HttpStatus.UNAUTHORIZED)
      .code("auth.accessDenied")
      .build();
    return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
  }

}
