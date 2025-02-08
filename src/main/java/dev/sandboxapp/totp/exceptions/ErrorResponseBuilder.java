package dev.sandboxapp.totp.exceptions;

import dev.sandboxapp.totp.dto.Error;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorResponseBuilder {

  public final List<Error> errors = new ArrayList<>();

  public List<Error> build() {
    return errors;
  }

  public ErrorBuilder add() {
    return new ErrorBuilder(this);
  }

  public static class ErrorBuilder {
    private final ErrorResponseBuilder parent;

    private String code;
    private String detail;
    private String title;
    private HttpStatus status;
    private Map<String, String> meta;

    public ErrorBuilder(ErrorResponseBuilder parent) {
      this.parent = parent;
    }

    public ErrorBuilder code(String code) {
      this.code = code;
      return this;
    }

    public ErrorBuilder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public ErrorBuilder title(String title) {
      this.title = title;
      return this;
    }

    public ErrorBuilder status(HttpStatus status) {
      this.status = status;
      return this;
    }

    public ErrorBuilder meta(Map<String, String> meta) {
      this.meta = meta;
      return this;
    }

    public ErrorBuilder add() {
      parent.errors.add(new Error(
        code,
        detail,
        title,
        status,
        meta
      ));
      return new ErrorBuilder(parent);
    }

    public List<Error> build() {
      return parent.build();
    }
  }
}
