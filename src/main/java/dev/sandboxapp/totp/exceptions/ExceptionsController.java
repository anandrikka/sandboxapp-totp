package dev.sandboxapp.totp.exceptions;

import dev.sandboxapp.totp.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionsController {

  @ExceptionHandler({ AuthenticationException.class })
  ResponseEntity<ErrorMessage> unauthourizedException(AuthenticationException ex) {
    var errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage(), ex.getMessage(), LocalDateTime.now());
    return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
  }
}
