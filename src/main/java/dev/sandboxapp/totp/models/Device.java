package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Setter
@Getter
public class Device extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String deviceName;

  @Column(nullable = false)
  private String deviceToken;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "device", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<UserDeviceSession> userSessions;
}
