package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.dto.requests.SignInConfirm;
import dev.sandboxapp.totp.dto.requests.SignInRequest;
import dev.sandboxapp.totp.dto.requests.SignupRequest;
import dev.sandboxapp.totp.models.AccessToken;
import dev.sandboxapp.totp.models.Device;
import dev.sandboxapp.totp.repositories.AccessTokenRepository;
import dev.sandboxapp.totp.repositories.ActivationTokenRepository;
import dev.sandboxapp.totp.repositories.DeviceRepository;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.services.AuthenticationService;
import dev.sandboxapp.totp.services.EmailService;
import dev.sandboxapp.totp.services.JwtTokenService;
import dev.sandboxapp.totp.utils.AuthUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AccessTokenRepository accessTokenRepo;
  private final ActivationTokenRepository activationTokenRepo;
  private final JwtTokenService jwtTokenService;
  private final DeviceRepository deviceRepo;
  private final EmailService emailService;
  private final AuthenticationService authenticationService;

  @PostMapping("/signup")
  void createNewUser(@RequestBody @Valid SignupRequest request) throws Exception {
    authenticationService.createAccount(request);
  }

  @GetMapping("/signup_verification")
  void verifySignup(
    @RequestParam(name = "email") String email,
    @RequestParam(name = "token") String token) {
    authenticationService.verifySignupRequest(email, token);
  }

  @GetMapping("/resend_signup_verification_link")
  void requestSignupVerificationLink(@RequestParam(name = "email") String email) throws MessagingException {
    authenticationService.resendVerificationLink(email);
  }

  @PostMapping("/signin")
  void signInUser(@RequestBody SignInRequest request) throws Exception {
    // var user = userRepo.findByEmail(request.email()).orElseThrow(() -> new AppLoginException("Email not registered", request.email()));
    // if (!user.isActivated()) {
    //   throw new Exception("Activate user before login");
    // }
    // var loginToken = AuthUtils.generateLoginToken();
    // var accessToken = new AccessToken();
    // accessTokenRepo.save(accessToken);
    // Map<String, Object> inputs = new HashMap<>();
    // inputs.put("name", user.getFirstName() + " " + user.getLastName());
    // inputs.put("otp", loginToken);
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    // String formattedDate = LocalDateTime.now().format(formatter);
    // inputs.put("date", formattedDate);
    // emailService.sendEmail(request.email(), "Login OTP", "one-time-password", inputs);
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
