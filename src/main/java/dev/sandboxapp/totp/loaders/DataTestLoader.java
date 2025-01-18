package dev.sandboxapp.totp.loaders;

import dev.sandboxapp.totp.models.*;
import dev.sandboxapp.totp.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataTestLoader implements CommandLineRunner {

  private final UserRepository userRepo;
  private final AccountRepository accountRepo;
  private final DeviceRepository deviceRepo;
  private final UserDeviceSessionRepository userSessionRepo;

  public DataTestLoader(
    UserRepository userRepo,
    AccountRepository accountRepo,
    DeviceRepository deviceRepo,
    UserDeviceSessionRepository userSessionRepo
  ) {
    this.userRepo = userRepo;
    this.accountRepo = accountRepo;
    this.deviceRepo = deviceRepo;
    this.userSessionRepo = userSessionRepo;
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
    user.setPhoneNumber("+17813539780");
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

  void createUserSession(User user, Device device) {
    UserDeviceSession userSession = new UserDeviceSession();
    userSession.setDevice(device);
    userSession.setUser(user);
    userSession.setRefreshToken("abc");
    userSession.setRefreshTokenExpiresAt(LocalDateTime.now());
    userSessionRepo.save(userSession);
  }
}
