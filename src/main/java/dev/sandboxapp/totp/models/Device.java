package dev.sandboxapp.totp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "devices")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Device extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public UUID id;

  @Column(nullable = false, updatable = false)
  public String deviceName;

  @Column(nullable = false, updatable = false)
  @JsonIgnore
  public String deviceToken;

  @Column(updatable = true)
  @JsonIgnore
  public LocalDateTime rememberUntil;

  @Column
  public boolean rememberMe;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  public User user;
}
