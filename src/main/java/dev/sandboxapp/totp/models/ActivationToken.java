package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "activation_tokens")
@NoArgsConstructor
@Setter
@Getter
public class ActivationToken extends Auditable {

  @Id
  @Column
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(updatable = false, nullable = false)
  private String token;

  @Column
  private LocalDateTime expiresAt;

  @Column
  private LocalDateTime usedAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
