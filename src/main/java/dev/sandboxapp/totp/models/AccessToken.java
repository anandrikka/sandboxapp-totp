package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "access_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessToken extends Auditable {

  public AccessToken(User user, String deviceToken, String authToken, LocalDateTime expiresAt) {
    this.user = user;
    this.deviceToken = deviceToken;
    this.authToken = authToken;
    this.expiresAt = expiresAt;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public UUID id;

  @Column(updatable = false, nullable = false)
  public String authToken;

  @Column(updatable = false, nullable = false)
  public LocalDateTime expiresAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @Column(nullable = false, updatable = false)
  public String  deviceToken;

  @Column
  public boolean verified = false;
}
