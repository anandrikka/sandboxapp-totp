package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.dto.requests.SignInConfirm;
import dev.sandboxapp.totp.dto.requests.SignInRequest;
import dev.sandboxapp.totp.dto.requests.SignupRequest;
import dev.sandboxapp.totp.models.AccessToken;
import dev.sandboxapp.totp.models.Device;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.AccessTokenRepository;
import dev.sandboxapp.totp.repositories.DeviceRepository;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.services.AuthenticationService;
import dev.sandboxapp.totp.services.JwtTokenService;
import dev.sandboxapp.totp.utils.AuthUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AuthenticationService authService;
  private final AccessTokenRepository accessTokenRepo;
  private final JwtTokenService jwtTokenService;
  private final DeviceRepository deviceRepo;

  @PostMapping("/signup")
  String createNewUser(@RequestBody @Valid SignupRequest request) throws Exception {
    var user = userRepo.findByEmail(request.email()).orElse(new User());
    if (user.usableId() != null) {
      if (user.isActivated()) {
        throw new Exception("User exists");
      }
    } else {
      user.setEmail(request.email());
      user.setFirstName(request.firstName());
      user.setLastName(request.lastName());
      userRepo.save(user);
    }
    return AuthUtils.generateSignupValidationToken(user.usableId());
  }

  @GetMapping("/verify_signup")
  Boolean verifySignup(
    @RequestParam(name = "email", required = true) String email,
    @RequestParam(name = "token", required = true) String token) throws Exception {
    var user = userRepo.findByEmail(email).orElseThrow(() -> new Exception("Invalid Request."));
    if (user.isActivated()) {
      throw new Exception("Already activated.");
    }
    if (!AuthUtils.verifySignupValidationToken(user.usableId(), token)) {
      throw new Exception("Verification failed.");
    }
    user.setActivated(true);
    userRepo.save(user);
    return true;
  }

  @PostMapping("/signin")
  ResponseEntity<String> signInUser(@RequestBody SignInRequest request) throws Exception {
    var user = userRepo.findByEmail(request.email()).orElseThrow(() -> new Exception("Unable to find user"));
    if (!user.isActivated()) {
      throw new Exception("Activate user before login");
    }
    var loginToken = AuthUtils.generateLoginToken();
    var accessToken = new AccessToken(
      user,
      request.deviceToken(),
      loginToken,
      LocalDateTime.now().plusMinutes(10)
    );
    accessTokenRepo.save(accessToken);
    return ResponseEntity.ok(accessToken.getAuthToken());
  }

  @PostMapping("/verify_signin")
  String verifyOtp(@RequestBody SignInConfirm request)  throws Exception {
    var user = userRepo.findByEmail(request.email()).orElseThrow(() -> new Exception("Invalid User"));
    accessTokenRepo.findValidAccessToken(
      user.getId(),
      request.deviceToken(),
      request.authToken(),
      LocalDateTime.now()
    ).orElseThrow(() -> new Exception("Login failed."));
    var resiteredDevice = deviceRepo.findByUserIdAndDeviceToken(user.getId(), request.deviceToken());
    if (resiteredDevice.isEmpty()) {
      var device = new Device();
      device.setUser(user);
      device.setDeviceName("Demo");
      device.setDeviceToken(request.deviceToken());
      device.setRememberMe(request.rememberMe());
      if (request.rememberMe()) {
        device.setRefreshToken(AuthUtils.randomToken());
      }
      deviceRepo.save(device);
    }
    return jwtTokenService.generateToken(user);
  }

  @GetMapping("/invalidate_token")
  void invalidateToken() {}
}
