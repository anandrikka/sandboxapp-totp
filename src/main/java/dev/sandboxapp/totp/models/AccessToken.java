package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "access_tokens")
@Getter
@Setter
public class AccessToken extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public UUID id;

  @Column
  public String authToken;

  @Column
  public LocalDateTime expiresAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @Column(nullable = false)
  public String  deviceToken;
}
