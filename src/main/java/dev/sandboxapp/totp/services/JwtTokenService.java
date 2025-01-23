package dev.sandboxapp.totp.services;

import dev.sandboxapp.totp.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class JwtTokenService {

  private final JwtProperties jwtProperties;

  public String generateToken(String username) {
    long EXPIRATION_TIME = jwtProperties.getExpirationTime() * 1000L;
    Map<String, String> claims = new HashMap<>();
    return Jwts
      .builder()
      .claims()
      .add(claims)
      .issuer("sandboxapp.dev")
      .subject(username)
      .issuedAt(new Date())
      .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
      .and()
      .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
      .compact();
  }

  public Claims extractClaims(String token) {
    return Jwts
      .parser()
      .verifyWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
      .build()
      .parseSignedClaims(token)
      .getPayload();
  }

  public boolean expired(String token) {
    return extractClaims(token).getExpiration().before(new Date());
  }

  public String username(String token) {
    return extractClaims(token).getSubject();
  }

}
