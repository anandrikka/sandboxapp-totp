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
public class User extends Base{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private UUID id;

  @Column(
    unique = true,
    nullable = false,
    length = 255
  )
  private String email;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column(nullable = false, unique = true, length = 15)
  private String phoneNumber;

  @Column
  private String gender;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Account> accounts = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Device> devices;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<UserDeviceSession> userDeviceSessions;
}
