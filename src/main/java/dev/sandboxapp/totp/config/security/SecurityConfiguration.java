package dev.sandboxapp.totp.config.security;

import dev.sandboxapp.totp.services.UserDetailsServiceImpl;
import jakarta.servlet.DispatcherType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final UserDetailsServiceImpl userDetailsService;
  private final AuthenticationEntryPoint authEntryPoint;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(customizer ->
        customizer
          // By default, spring protects all dispatcher types assuming every forwarding route might be protected
          // In my case I need to ignore error, because it's only Rest API.
          // .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
          .requestMatchers(
            "/heartbeat",
            "/",
            "/index.html",
            "/assets/**",
            "/api/v1/auth/**"
          ).permitAll()
          .anyRequest().authenticated()
      )
      .exceptionHandling(customizer -> {
        customizer.authenticationEntryPoint(authEntryPoint);
      })
      .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
      .build();
  }
}
