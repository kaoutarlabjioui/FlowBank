package org.example.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.UUID;

public class Account {

    private String accountId;
    private UUID ownerUserId;
    private BigDecimal balance;
    private Instant createdAt;
    private boolean active;
    private AccountType accountType;

    public enum AccountType {
        standard,
        savings,
        credit

    }

public Account( UUID ownerUserId,  AccountType accountType ) {
    this.accountId = generateAccountId();
    this.ownerUserId = ownerUserId;
    this.balance = BigDecimal.ZERO.setScale(2 ,  RoundingMode.HALF_EVEN);
    this.createdAt = Instant.now();
    this.active = true;
    this.accountType = accountType;
}

    private String generateAccountId() {
        int random1 = (int)(Math.random() * 10000);
        int random2 = (int)(Math.random() * 10000);
        String uuidPart = UUID.randomUUID().toString().substring(0, 4);
        return "BK-" + random1 + "-" + random2 + "-" + uuidPart;
    }



    public String getAccountId() {
    return accountId;
}
    public UUID getOwnerUserId() {
    return ownerUserId;
}

   public AccountType getType() {
    return accountType;
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
    public void setActive(boolean active) {
        this.active = active;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
public AccountType getAccountType() {
        return accountType;
}
    public void setType(AccountType type) {
        this.accountType = type;
    }
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public  void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
    }
    public void transfer(BigDecimal amount, Account destination) {
        this.balance = this.balance.subtract(amount);
        destination.deposit(amount);
    }
    public void transfer(BigDecimal amount, String RIB) {
        this.balance = this.balance.subtract(amount);
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
