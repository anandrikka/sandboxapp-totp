package dev.sandboxapp.totp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends Auditable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  public UUID id;

  @Column(
    unique = true,
    nullable = false,
    length = 255
  )
  public String email;

  @Column
  public String firstName;

  @Column
  public String lastName;

  @Column
  public boolean activated = false;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Account> accounts = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  public List<Device> devices;

  public String usableId() {
    return id != null ? id.toString() : null;
  }
}
