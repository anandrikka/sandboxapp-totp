package dev.sandboxapp.totp.dto;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public record ErrorRecord(
  String code,
  String detail,
  String title,
  HttpStatus status,
  Map<String, Object> meta
) {
  public static class Builder {
    private String code;
    private String message;
    private String title;
    private HttpStatus status;
    private final Map<String, Object> metaData = new HashMap<>();

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder status(HttpStatus status) {
      this.status = status;
      this.title = status.getReasonPhrase();
      return this;
    }

    public Builder meta(String key, Object value) {
      this.metaData.put(key, value);
      return this;
    }

    public ErrorRecord build() {
      return new ErrorRecord(code, message, title, status, metaData);
    }
  }

  public static Builder builder() {
    return new Builder();
  }
}
