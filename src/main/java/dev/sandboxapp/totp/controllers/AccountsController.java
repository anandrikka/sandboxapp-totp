package dev.sandboxapp.totp.controllers;

import dev.sandboxapp.totp.dto.requests.AccountRequest;
import dev.sandboxapp.totp.models.Account;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountsController {

  @PostMapping("")
  Account createAccount(@RequestBody AccountRequest request) {
    return new Account();
  }
}
