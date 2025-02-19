package dev.sandboxapp.totp.services;

import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.CodeGenerationException;
import dev.samstevens.totp.time.TimeProvider;
import dev.sandboxapp.totp.dto.AccountOneTimePasscodes;
import dev.sandboxapp.totp.dto.OneTimePasscode;
import dev.sandboxapp.totp.models.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class TotpService {
  private final TimeProvider timeProvider;

  // Supported Alogrithms: SHA1, SHA256, SHA512
  public AccountOneTimePasscodes generateMultipleOTP(Account account) {
    var codeGenerator = new DefaultCodeGenerator(HashingAlgorithm.valueOf(account.getAlgorithm()), account.getDigits());
    long currentTimeSeconds = timeProvider.getTime();
    AtomicLong counter = new AtomicLong((currentTimeSeconds / account.getPeriod()) - 1);
    List<OneTimePasscode> passcodes = new ArrayList<>();
    IntStream.range(0, 3).forEach(i -> {
      var inc = counter.addAndGet(1);
      try {
        passcodes.add(new OneTimePasscode(inc, codeGenerator.generate(account.getSecret(), inc)));
      } catch (CodeGenerationException e) {
        throw new RuntimeException(e);
      }
    });
    return new AccountOneTimePasscodes(
      account.getId(),
      account.getPeriod(),
      passcodes
    );
  }
}
