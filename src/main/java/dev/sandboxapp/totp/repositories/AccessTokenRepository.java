package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.AccessToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, UUID> {

  @Query("Select acc_token from AccessToken acc_token where acc_token.user.id = :userId and acc_token.authToken = :authToken and acc_token.expiresAt > :now")
  Optional<AccessToken> findValidAccessToken(
    UUID userId,
    String deviceToken,
    String authToken,
    LocalDateTime now
  );
}
