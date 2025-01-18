package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_device_sessions")
@Setter
@Getter
public class UserDeviceSession extends Base {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true, nullable = false)
  private String refreshToken;

  @Column(nullable = false)
  private LocalDateTime refreshTokenExpiresAt;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "device_id")
  private Device device;
}
