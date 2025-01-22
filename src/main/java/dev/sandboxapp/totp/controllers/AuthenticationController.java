package dev.sandboxapp.totp.controllers;

import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.sandboxapp.totp.dto.requests.SignInRequest;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.services.AuthenticationService;
import dev.sandboxapp.totp.services.JwtTokenService;
import dev.sandboxapp.totp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AuthenticationService authService;
  private final UserService userService;
  private final JwtTokenService jwtTokenService;

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
  String verifyOtp(@RequestBody SignInRequest request)  throws Exception {
    return authService.verifySignInOtp(request.emailOrPhoneNumber(), request.code());
  }

  @GetMapping("/refresh_token")
  String refreshToken() throws CodeGenerationException {
    var username = userService.getAuthenticatedUsername();
    if (username != null) {
      return jwtTokenService.generateToken(username);
    }
    return "";
  }
}
