package org.example.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Account {

    private String id;
    private UUID ownerUserId;
    private BigDecimal balance;
    private Instant createdAt;
    private boolean active;



public Account(UUID ownerUserId, BigDecimal balance, Instant createdAt, boolean active) {
    this.ownerUserId = ownerUserId;
    this.balance = balance;
    this.createdAt = createdAt;
    this.active = active;
}

    @Override
    public String toString() {
        return super.toString();
    }
}
