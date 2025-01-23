package dev.sandboxapp.totp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Setter
@Getter
public class Device extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public UUID id;

  @Column(nullable = false)
  public String deviceName;

  @Column(nullable = false)
  @JsonIgnore
  public String deviceToken;

  @ManyToOne
  @JoinColumn(name = "user_id")
  public User user;

  @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<UserDeviceSession> userDeviceSessions;
}
