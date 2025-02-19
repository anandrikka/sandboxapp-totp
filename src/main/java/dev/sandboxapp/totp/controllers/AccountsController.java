package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.dto.AccountOneTimePasscodes;
import dev.sandboxapp.totp.dto.requests.AccountRequest;
import dev.sandboxapp.totp.exceptions.ExceptionUtils;
import dev.sandboxapp.totp.models.Account;
import dev.sandboxapp.totp.models.User;
import dev.sandboxapp.totp.repositories.AccountRepository;
import dev.sandboxapp.totp.services.TotpService;
import dev.sandboxapp.totp.utils.AuthUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

  private final AccountRepository accountRepo;
  private final TotpService totpService;

  @PostMapping("")
  ResponseEntity createAccount(@RequestBody AccountRequest request) {
    var userId = AuthUtils.loggedInUserId();
    Account account = new Account();
    var user = new User();
    user.setId(userId);
    account.setUser(user);
    account.setIssuer(request.issuer());
    account.setName(request.name());
    account.setSecret(request.secret());
    account.setDigits(6);
    account.setPeriod(30);
    accountRepo.save(account);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{id}")
  void deleteAccount(@PathVariable UUID id) {
    accountRepo.deleteById(id);
  }

  @GetMapping("")
  List<Account> fetchAccounts() {
    var userId = AuthUtils.loggedInUserId();
    return accountRepo.findAllByUserId(userId);
  }

  @GetMapping("/{id}/otps")
  AccountOneTimePasscodes fetchOTPs(@PathVariable UUID id) {
    var account = accountRepo.findById(id).orElseThrow(() -> ExceptionUtils.accountNotFound(id));
    return totpService.generateMultipleOTP(account);
  }
}
