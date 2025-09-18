package org.example.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Transaction {

    public enum Type {DEPOSIT, WITHDRAW , TRANSFERIN , TRANSFEROUT };
    private UUID id;
    private Instant timestamp;
    private String accountId;
   private Type type;
   private BigDecimal amount;
   private String counterpartyAccountId;
   private String description

    public Transaction(String accountId, Type type, BigDecimal amount, String counterpartyAccountId, String description) {
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
        this.accountId = accountId;
        this.type = type;
        this.amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
        this.counterpartyAccountId = counterpartyAccountId;
        this.description = description;

    } public UUID getId() {
       return id;
   }
    public Instant getTimestamp() {
       return timestamp;
   }
    public String getAccountId() {
       return accountId;
   }
    public Type getType() {
       return type;
   }
    public BigDecimal getAmount() {
       return amount;
   }
    public String getCounterpartyAccountId() {
       return counterpartyAccountId;
   }
    public String getDescription() {
       return description;
   }
    @Override
    public String toString() {
        return super.toString();
    }
}
