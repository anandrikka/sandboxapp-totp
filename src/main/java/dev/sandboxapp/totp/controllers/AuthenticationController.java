package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.config.JwtProperties;
import dev.sandboxapp.totp.dto.requests.SignInTokenRequest;
import dev.sandboxapp.totp.dto.requests.SignInVerificationRequest;
import dev.sandboxapp.totp.dto.requests.SignupRequest;
import dev.sandboxapp.totp.models.Device;
import dev.sandboxapp.totp.repositories.AccessTokenRepository;
import dev.sandboxapp.totp.repositories.ActivationTokenRepository;
import dev.sandboxapp.totp.repositories.DeviceRepository;
import dev.sandboxapp.totp.repositories.UserRepository;
import dev.sandboxapp.totp.services.AuthenticationService;
import dev.sandboxapp.totp.services.EmailService;
import dev.sandboxapp.totp.services.JwtTokenService;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AccessTokenRepository accessTokenRepo;
  private final ActivationTokenRepository activationTokenRepo;
  private final JwtTokenService jwtTokenService;
  private final DeviceRepository deviceRepo;
  private final EmailService emailService;
  private final AuthenticationService authService;
  private final JwtProperties jwtProperties;

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
    var deviceToken = request.getHeader("X-VISITOR-ID");
    var deviceName = request.getHeader("User-Agent");
    var jwtToken = authService.verifySignInToken(body, deviceName, deviceToken);
    var isProduction = "development".equals(activeProfile);
    Cookie cookie = new Cookie("_auth", jwtToken);
    cookie.setHttpOnly(isProduction);
    cookie.setSecure(isProduction);
    cookie.setMaxAge(jwtProperties.getExpirationTime());
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  @GetMapping("/invalidate_token")
  void invalidateToken() {}
}
