package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.config.JwtProperties;
import dev.sandboxapp.totp.dto.requests.SignInTokenRequest;
import dev.sandboxapp.totp.dto.requests.SignInVerificationRequest;
import dev.sandboxapp.totp.dto.requests.SignupRequest;
import dev.sandboxapp.totp.exceptions.ExceptionUtils;
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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final AuthenticationService authService;
  private final JwtProperties jwtProperties;
  private final JwtTokenService jwtTokenService;
  private final UserRepository userRepository;
  private final DeviceRepository deviceRepository;

  @Value("${spring.profiles.active:default}")
  private String activeProfile;

  @PostMapping("/signup")
  void createNewUser(@RequestBody @Valid SignupRequest request) throws Exception {
    authService.createAccount(request);
  }

  @GetMapping("/signup_verification")
  void verifySignup(
    @RequestParam(name = "email") String email,
    @RequestParam(name = "token") String token) {
    authService.verifySignupRequest(email, token);
  }

  @GetMapping("/resend_signup_verification_link")
  void requestSignupVerificationLink(@RequestParam(name = "email") String email) throws MessagingException {
    authService.resendVerificationLink(email);
  }

  @PostMapping("/signin_token")
  void signInUser(@RequestBody SignInTokenRequest body, HttpServletRequest request) throws Exception {
    var deviceToken = request.getHeader("X-VISITOR-ID");
    authService.sendSignInToken(body.email(), deviceToken);
  }

  @PostMapping("/verify_signin")
  void verifyOtp(@RequestBody SignInVerificationRequest body, HttpServletRequest request, HttpServletResponse response) {
    var deviceToken = AuthUtils.getDeviceToken(request);
    var deviceName = request.getHeader("User-Agent");
    var device = authService.verifySignInToken(body, deviceName, deviceToken);
    var jwtToken = jwtTokenService.generateToken(device.getUser());
    response.addCookie(createCookie("_auth", jwtToken, jwtProperties.getExpirationTime()));
    if (body.rememberMe()) {
      response.addCookie(createCookie("_ref_id", device.getId().toString(), Integer.MAX_VALUE));
    }
  }

  @GetMapping("/refresh_token")
  void refershToken(HttpServletRequest request, HttpServletResponse response) {
    var deviceId = AuthUtils.getCookie("_ref_id", request);
    var user = authService.verifyDeviceForRefreshToken(deviceId, AuthUtils.getDeviceToken(request));
    var newToken = jwtTokenService.generateToken(user);
    var cookie = createCookie("_auth", newToken, jwtProperties.getExpirationTime());
    response.addCookie(cookie);
  }

  private Cookie createCookie(String name, String value, int maxAge) {
    var isProduction = "development".equals(activeProfile);
    Cookie cookie = new Cookie(name, value);
    cookie.setHttpOnly(isProduction);
    cookie.setSecure(isProduction);
    if (maxAge > 0) {
      cookie.setMaxAge(maxAge);
    }
    cookie.setPath("/");
    return cookie;
  }
}
