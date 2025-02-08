package dev.sandboxapp.totp.exceptions;

import dev.sandboxapp.totp.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionAdvicer {

  @ExceptionHandler({ AuthenticationException.class })
  ResponseEntity<List<Error>> unauthourizedException(AuthenticationException ex) {
    List<Error> errors = new ErrorResponseBuilder()
      .add()
      .code("auth.unauthorized")
      .detail(ex.getMessage())
      .title(HttpStatus.UNAUTHORIZED.getReasonPhrase())
      .status(HttpStatus.UNAUTHORIZED)
      .build();
    return new ResponseEntity<>(errors, HttpStatus.UNAUTHORIZED);
  }
}
