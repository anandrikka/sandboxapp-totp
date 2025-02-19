package dev.sandboxapp.totp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public UUID id;

  @Column(nullable = false)
  public String name;

  @Column
  public String issuer;

  @Column(nullable = false, updatable = false)
  @JsonIgnore
  public String secret;

  @Column
  @JsonIgnore
  public Integer digits = 6;

  @Column
  @JsonIgnore
  public Integer period = 30;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(nullable = false, name = "user_id")
  public User user;

  @Column(nullable = false, updatable = false)
  public String algorithm = "SHA1";
}
