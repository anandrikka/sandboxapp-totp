package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.ActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivationTokenRepository extends JpaRepository<ActivationToken, UUID> {

  Optional<ActivationToken> findByUserIdAndToken(UUID userId, String token);

  @Modifying
  @Transactional
  @Query("UPDATE ActivationToken a SET a.usedAt = :usedAt WHERE a.user.id = :userId and a.usedAt IS NULL")
  void markAllTokensAsUsed(@Param("userId") UUID userId, @Param("usedAt") LocalDateTime usedAt);
}
