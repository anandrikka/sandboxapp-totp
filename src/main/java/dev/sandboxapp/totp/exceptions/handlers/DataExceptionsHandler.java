package dev.sandboxapp.totp.exceptions.handlers;

import dev.sandboxapp.totp.dto.ErrorRecord;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataExceptionsHandler {
  
  @ExceptionHandler(DataIntegrityViolationException.class)
  ResponseEntity<ErrorRecord> catchUniqueValidationErrors(DataIntegrityViolationException e) {
    return new ResponseEntity<>(ErrorRecord.builder()
      .code("data.integrity.error")
      .message(e.getMostSpecificCause().getMessage())
      .status(HttpStatus.UNPROCESSABLE_ENTITY)
      .build(), HttpStatus.UNPROCESSABLE_ENTITY);
  }
  
}
