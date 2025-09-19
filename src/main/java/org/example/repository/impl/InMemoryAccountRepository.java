package org.example.repository.impl;

import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepository;

import java.util.*;

import java.util.stream.Collectors;
public class InMemoryAccountRepository implements AccountRepository {

    private final Map<String, Account> store = new HashMap<>();

    @Override
   public String save(Account account) {
        boolean exists = store.values().stream()
                        .anyMatch(a->a.getOwnerUserId()
                                .equals(account.getOwnerUserId()) && a.getType() == account.getType());

     if(exists){
         return "This user already has a " + account.getType() + " account!";
     }

     store.put(account.getAccountId(), account);
     return "✅ Account " + account.getType() + " created successfully!";
    }

    public Optional<Account> findByAccountId(String accountId){
        return store.values().stream()
                .filter(account->account.getAccountId().equals(accountId))
                .findFirst();
    }

    public List<Account> findByOwner(UUID ownerUserID){

        return store.values().stream()
                .filter(account -> account.getOwnerUserId().equals(ownerUserID))
                .toList();

    }




    public List<Account> all() {
        return new ArrayList<>(store.values());
    }

    @Override
   /* public List<Account> all(User user) {
        return store.values().stream()
                .filter(acc -> Objects.equals(user.getId(), acc.getOwnerUserId()))
                .toList();
    }*/

    public Optional<Account> checkUserAccountType(User user, Account.AccountType accountType) {
        return findByOwner(user.getId()).stream()
                .filter(a -> a.getType() == accountType)
                .findFirst();

    }

    public void delete(Account account) {
        store.remove(account.getAccountId());
    }
}
