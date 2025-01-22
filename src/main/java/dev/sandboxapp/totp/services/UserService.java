package dev.sandboxapp.totp.services;

import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepo;

  public Optional<User> getAuthenticatedUser() {
    var username = getAuthenticatedUsername();
    if (username != null) {
      return userRepo.findByEmailOrPhoneNumber(username);
    }
    return null;
  }

  public String getAuthenticatedUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = null;
    if (authentication != null && authentication.isAuthenticated()) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof UserDetails) {
        username = ((UserDetails) principal).getUsername();
      } else {
        username = principal.toString();
      }
    }
    return username;
  }
}
