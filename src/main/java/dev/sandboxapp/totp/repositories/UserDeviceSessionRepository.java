package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.UserDeviceSession;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserDeviceSessionRepository extends CrudRepository<UserDeviceSession, UUID> {
}
