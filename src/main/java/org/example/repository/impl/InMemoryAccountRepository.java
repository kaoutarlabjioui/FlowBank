package org.example.repository.impl;

import org.example.model.Account;
import org.example.repository.AccountRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> store = new ConcurrentHashMap<>();

    @Override
   public String save(Account account) {
        store.put(account.getAccountId(),account);
    }

    public Account findByAccountId(String accountId){
        return store.get(accountId);
    }

    public List<Account> findByOwner(UUID ownerUserID){

        return store.values().stream().filter(account -> account.getOwnerUserId().equals(ownerUserID));

    }
}
