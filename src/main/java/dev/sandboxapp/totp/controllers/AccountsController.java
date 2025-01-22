package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.dto.requests.AccountRequest;
import dev.sandboxapp.totp.models.Account;
import dev.sandboxapp.totp.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

  private final AccountRepository accountRepo;

  @PostMapping("")
  Account createAccount(@RequestBody AccountRequest request) {
    return new Account();
  }

  // @GetMapping("")
  // List<Account> getAllAccounts() {
  //   return accountRepo.;
  // }
}
