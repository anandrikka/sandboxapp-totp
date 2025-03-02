package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

  private final UserRepository userRepo;

  @PutMapping("")
  ResponseEntity<User> updateUser() {
    var id = AuthUtils.loggedInUserId();
    return ResponseEntity.ok(userRepo.findById(id).get());
  }

  @DeleteMapping("")
  void deleteUser() {
    var id = AuthUtils.loggedInUserId();
    userRepo.deleteById(id);
  }

  @GetMapping("/self")
  ResponseEntity<User> self() {
    var id = AuthUtils.loggedInUserId();
    return ResponseEntity.ok(userRepo.findById(id).get());
  }

}
