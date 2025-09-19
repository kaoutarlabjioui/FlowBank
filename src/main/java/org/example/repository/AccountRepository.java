package org.example.repository;

import org.example.model.Account;
import org.example.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    String save(Account account);
    void delete(Account account);
    Optional<Account> findByAccountId(String accountId);
    List<Account> all();
    List<Account> findByOwner(UUID ownerUserID );
    Optional<Account> checkUserAccountType(User user, Account.AccountType accountType);
}
