package dev.sandboxapp.totp.repositories;

import dev.sandboxapp.totp.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountRepository extends CrudRepository<Account, UUID> {
}
