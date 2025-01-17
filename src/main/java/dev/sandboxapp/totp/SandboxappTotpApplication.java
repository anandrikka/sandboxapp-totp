package dev.sandboxapp.totp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SandboxappTotpApplication {

  public static void main(String[] args) {
    SpringApplication.run(SandboxappTotpApplication.class, args);
  }

}
