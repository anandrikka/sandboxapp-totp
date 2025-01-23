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
public class UserDeviceSession extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @ManyToOne
  @JoinColumn(name = "device_id")
  public Device device;

  @Column
  public LocalDateTime refreshUntil;

  @Column
  public Boolean rememberMe;
}
