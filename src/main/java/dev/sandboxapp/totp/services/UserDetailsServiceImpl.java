package dev.sandboxapp.totp.services;

import dev.sandboxapp.totp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = userRepo.findByEmailOrPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException("Usser not found"));
    return new User(username, "", true, true, true, true, new ArrayList<>());
  }
}
