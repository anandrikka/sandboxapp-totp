package dev.sandboxapp.totp.exceptions.handlers;

import dev.sandboxapp.totp.dto.ErrorRecord;
import dev.sandboxapp.totp.exceptions.GlobalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(GlobalException.class)
  ResponseEntity<ErrorRecord> catchAllErrors(GlobalException ex) {
    return new ResponseEntity<>(ex.error, ex.error.status());
  }
}
