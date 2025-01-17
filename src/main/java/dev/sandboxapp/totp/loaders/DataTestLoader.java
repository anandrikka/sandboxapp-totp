package dev.sandboxapp.totp.loaders;

import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataTestLoader implements CommandLineRunner {

  private final UserRepository userRepo;

  public DataTestLoader(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public void run(String... args) throws Exception {
    this.createUser();
  }

  void createUser() {
    User user = new User();
    user.setEmail("anand.demo@outlook.com");
    user.setUsername("anandrikka");
    this.userRepo.save(user);
  }
}
