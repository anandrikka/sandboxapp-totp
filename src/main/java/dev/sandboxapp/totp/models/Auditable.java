package dev.sandboxapp.totp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {
  @JsonIgnore
  @Column(nullable = false, updatable = false)
  @CreatedDate
  public LocalDateTime createdAt;

  @JsonIgnore
  @Column(nullable = false, updatable = true)
  @LastModifiedDate
  public LocalDateTime updatedAt;
}
