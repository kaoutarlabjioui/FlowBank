package org.example.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Account {

    private String accountId;
    private UUID ownerUserId;
    private BigDecimal balance;
    private Instant createdAt;
    private boolean active;



public Account( UUID ownerUserId, BigDecimal balance, Instant createdAt, boolean active) {
    this.accountId = "BK-" +(int)(Math.random()*10000) + "-" + (int)(Math.random()*10000);
    this.ownerUserId = ownerUserId;
    this.balance = BigDecimal.ZERO.setScale(2);
    this.createdAt = Instant.now();
    this.active = true;
}
    public String getAccountId() {
    return accountId;
}
    public UUID getOwnerUserId() {
    return ownerUserId;
}
    public BigDecimal getBalance() {
    return balance;
}
    public Instant getCreatedAt() {
    return createdAt;
}
    public boolean isActive() {
    return active;
}

    @Override
    public String toString() {
        return super.toString();
    }
}
