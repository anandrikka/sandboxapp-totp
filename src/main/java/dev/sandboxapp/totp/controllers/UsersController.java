package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

  private final UserRepository userRepo;

  @PutMapping("/{id}")
  ResponseEntity<User> updateUser(@PathVariable String id) {
    return ResponseEntity.ok(userRepo.findById(UUID.fromString(id)).get());
  }

  @DeleteMapping("/{id}")
  void deleteUser(@PathVariable String id) {}

}
