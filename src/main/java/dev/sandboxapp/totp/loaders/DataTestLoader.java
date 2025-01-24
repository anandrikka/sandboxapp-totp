package dev.sandboxapp.totp.loaders;

import dev.sandboxapp.totp.models.Account;
import dev.sandboxapp.totp.models.Device;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.AccountRepository;
import dev.sandboxapp.totp.repositories.DeviceRepository;
import dev.sandboxapp.totp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataTestLoader implements CommandLineRunner {

  private final UserRepository userRepo;
  private final AccountRepository accountRepo;
  private final DeviceRepository deviceRepo;

  public DataTestLoader(
    UserRepository userRepo,
    AccountRepository accountRepo,
    DeviceRepository deviceRepo
  ) {
    this.userRepo = userRepo;
    this.accountRepo = accountRepo;
    this.deviceRepo = deviceRepo;
  }

  @Override
  public void run(String... args) throws Exception {
    // var user = this.createUser();
    // this.createAccount(user);
    // var device = this.createDevice(user);
    // this.createUserSession(user, device);
  }

  User createUser() {
    this.userRepo.deleteAll();
    User user = new User();
    user.setEmail("anand.demo@outlook.com");
    this.userRepo.save(user);
    return user;
  }

  void createAccount(User user) {
    Account account = new Account();
    account.setName("Google");
    account.setUser(user);
    account.setIssuer("google");
    account.setSecret("abc");
    this.accountRepo.save(account);
  }

  Device createDevice(User user) {
    Device device = new Device();
    device.setDeviceToken("bac");
    device.setUser(user);
    device.setDeviceName("bac");
    this.deviceRepo.save(device);
    return device;
  }
}
