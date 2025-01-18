package dev.sandboxapp.totp.controllers;

import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.sandboxapp.totp.dto.requests.SignInRequest;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AuthenticationService authService;

  AuthenticationController(
    UserRepository userRepo,
    AuthenticationService authService
  ) {
    this.userRepo = userRepo;
    this.authService = authService;
  }

  @PostMapping("/signup")
  User createNewUser(@RequestBody User user) {
    return this.userRepo.save(user);
  }

  @PostMapping("/signin")
  ResponseEntity<String> signInUser(@RequestBody SignInRequest request) throws CodeGenerationException {
    String otp = authService.generateSignInOtp(request.emailOrPhoneNumber());
    return ResponseEntity.ok(otp);
  }

  @PostMapping("/verify")
  Boolean verifyOtp(@RequestBody SignInRequest request)  throws CodeGenerationException {
    return authService.verifySignInOtp(request.emailOrPhoneNumber(), request.code());
  }
}
