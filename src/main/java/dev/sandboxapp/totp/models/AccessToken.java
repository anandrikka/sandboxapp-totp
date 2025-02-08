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

  @ManyToOne
  @JoinColumn(name = "device_id")
  private Device device;
}
