package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account extends Base{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column
  private String name;

  @Column
  private String issuer;

  @Column(nullable = false, updatable = false)
  private String secret;

  @Column
  private Integer digits = 6;

  @Column
  private Integer period = 30;

  @ManyToOne
  @JoinColumn(nullable = false, name = "user_id")
  private User user;
}
