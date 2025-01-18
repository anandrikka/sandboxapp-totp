package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  @Query("SELECT u from User u WHERE u.email = :emailOrPhoneNumber OR u.phoneNumber = :emailOrPhoneNumber")
  Optional<User> findByEmailOrPhoneNumber(String emailOrPhoneNumber);
}
