package dev.sandboxapp.totp.services;

import dev.sandboxapp.totp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
    var user = userRepo.findById(UUID.fromString(id)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return new User(user.usableId(), "", true, true, true, true, new ArrayList<>());
  }
}
