package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "access_tokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(updatable = false, nullable = false)
  private String authToken;

  @Column(updatable = false, nullable = false)
  private LocalDateTime expiresAt;

  @Column
  private LocalDateTime usedAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(updatable = false, nullable = false)
  private String deviceToken;
}
