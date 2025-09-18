package org.example.repository;

import org.example.model.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    String save(Account account);
Optional<Account> findByAccountId(String accountId);
    List<Account> all();
    List<Account> findByOwner(UUID ownerUserID );
    void update(Account account);
}
