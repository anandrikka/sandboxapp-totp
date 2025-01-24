package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<Device, UUID> {

  List<Device> findAllByUserId(UUID userId);

  Optional<Device> findByUserIdAndDeviceToken(UUID userId, String deviceToken);
}
