package dev.sandboxapp.totp.exceptions;

import dev.sandboxapp.totp.dto.ErrorRecord;
import java.util.function.Consumer;

public class GlobalException extends RuntimeException {

  public ErrorRecord error;

  public GlobalException(Consumer<ErrorRecord.Builder> builder) {
    var errorBuilder = ErrorRecord.builder();
    builder.accept(errorBuilder);
    error = errorBuilder.build();
  }

}
