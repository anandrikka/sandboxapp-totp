package dev.sandboxapp.totp.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class DelegateAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private final HandlerExceptionResolver expectionResolver;

  public DelegateAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver expectionResolver) {
    this.expectionResolver = expectionResolver;
  }

  @Override
  public void commence(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException authException
  ) throws IOException, ServletException {
    expectionResolver.resolveException(request, response, null, authException);
  }
}
