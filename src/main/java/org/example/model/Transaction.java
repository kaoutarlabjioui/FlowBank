package org.example.model;

import java.time.Instant;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private Instant timestamp;
    private String accountId;
    private String transactionId;

    public Transaction(UUID id, Instant timestamp, String accountId, String transactionId) {
        this.id = id;
        this.timestamp = timestamp;
        this.accountId = accountId;
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
