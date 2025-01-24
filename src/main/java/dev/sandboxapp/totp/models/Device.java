package dev.sandboxapp.totp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

  @Column(updatable = false)
  @JsonIgnore
  public String refreshToken;

  @Column
  public boolean rememberMe;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  public User user;
}
