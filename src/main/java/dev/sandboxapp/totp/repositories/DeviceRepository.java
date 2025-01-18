package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRepository extends CrudRepository<Device, UUID> {
}
