package dev.sandboxapp.totp.config;

import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TotpConfiguration {

  @Bean
  public TimeProvider getTimeProvider() {
    return new SystemTimeProvider();
  }

  @Bean
  public DefaultCodeGenerator codeGenerator() {
    return new DefaultCodeGenerator();
  }

  @Bean
  public DefaultCodeVerifier codeVerifier() {
    return new DefaultCodeVerifier(this.codeGenerator(), this.getTimeProvider());
  }
}
