package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.AccessToken;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, UUID> {

  @Query("Select acc_token from AccessToken acc_token where acc_token.user.id = :userId and acc_token.authToken = :authToken and acc_token.deviceToken = :deviceToken and acc_token.usedAt IS NULL")
  Optional<AccessToken> findValidAccessToken(
    @Param("userId") UUID userId,
    @Param("deviceToken") String deviceToken,
    @Param("authToken") String authToken
  );

  @Modifying
  @Query("UPDATE AccessToken a SET a.usedAt = :usedAt WHERE a.user.id = :userId AND a.usedAt IS NULL")
  void markAllTokensAsUsed(@Param("userId") UUID userId, @Param("usedAt") LocalDateTime usedAt);
}
