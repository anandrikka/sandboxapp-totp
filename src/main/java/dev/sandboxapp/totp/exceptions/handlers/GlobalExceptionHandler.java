package dev.sandboxapp.totp.exceptions.handlers;

import dev.sandboxapp.totp.dto.ErrorRecord;
import dev.sandboxapp.totp.exceptions.GlobalException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(GlobalException.class)
  ResponseEntity<ErrorRecord> catchAllErrors(GlobalException ex) {
    return new ResponseEntity<>(ex.error, ex.error.status());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    var errors = ex.getBindingResult().getFieldErrors().stream().collect(
      Collectors.toMap(
        FieldError::getField,
        FieldError::getDefaultMessage
      )
    );
    var result = new HashMap<String, Object>();
    result.put("code", "auth.signup.invalidForm");
    result.put("meta", errors);
    result.put("status", HttpStatus.BAD_REQUEST.value());
    result.put("title", HttpStatus.BAD_REQUEST.getReasonPhrase());
    result.put("message", "Invalid Form");
    return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
  }
}
