package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.models.Device;
import dev.sandboxapp.totp.repositories.DeviceRepository;
import dev.sandboxapp.totp.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/devices")
public class DevicesController {
  private final DeviceRepository deviceRepo;

  @GetMapping("")
  List<Device> fetchAllDevices() {
    var user = AuthUtils.loggedInUserId();
    return deviceRepo.findAllByUserId(user);
  }

  @DeleteMapping("/{id}")
  void removeDevice(@PathVariable String id) {
    deviceRepo.deleteById(UUID.fromString(id));
  }

}
