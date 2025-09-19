package org.example.repository.impl;

import org.example.model.Transaction;
import org.example.repository.TransactionRepository;

import java.util.*;

public class InMemoryTransactionRepository implements TransactionRepository {

    private static final Set<Transaction> transactions = new HashSet<>();

    public String save(Transaction transaction) {
        transactions.add(transaction);
        return "Transaction : " + transaction.getId() + "recorded successfully";
    }

    public Optional<Transaction> findById(UUID id) {
        return transactions.stream()
                .filter(transaction -> transaction.getId().equals(id))
                .findFirst();
    }

    public List<Transaction> findAll() {
        return ArrayList<>(transactions);
    }

    public List<Transaction> findByAccountId(String accountId) {

        return transactions.stream()
                .filter(t->t.getAccountId().equals(accountId))
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .toList();
    }





}
