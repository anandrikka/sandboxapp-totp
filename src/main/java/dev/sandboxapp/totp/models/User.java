package dev.sandboxapp.totp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(
    unique = true,
    nullable = false,
    length = 255
  )
  @Setter
  private String username;

  @Column(
    unique = true,
    nullable = false,
    length = 255
  )
  @Setter
  private String email;

  @CreatedDate
  @Column(updatable = false, nullable = false)
  @Setter
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(updatable = true, nullable = false)
  @Setter
  private LocalDateTime updatedAt;
}
