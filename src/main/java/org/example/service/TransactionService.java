package org.example.service;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.TransactionRepository;
import org.example.utils.AmountUtils;
import org.example.utils.ConsoleUtils;

import java.math.BigDecimal;

import static org.example.model.Transaction.Type.DEPOSIT;

public class TransactionService {

    public TransactionRepository transactionRepository;
    public AccountRepository accountRepository;
    public TransactionService(TransactionRepository transactionRepository , AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;

    }

    public void deposit(){
        Account account = AccountService.getAccount();
        if (account == null) {
            return;
        }

        BigDecimal amount = ConsoleUtils.readPositiveBigDecimal("Enter amount to deposit : ");
        String description = ConsoleUtils.readString("Enter description : ");
        account.deposit(amount);
        Transaction transaction = new Transaction(account.getAccountId(),DEPOSIT,amount,account.getAccountId(),description);
        transactionRepository.save(transaction);
        System.out.println(" Deposit of " + amount +  "successful. New Balance : "+ account.getBalance());
        return;
    }

    public void withdraw(){
        Account account = AccountService.getAccount();
        if (account == null){
            return;
        }
        BigDecimal amount = ConsoleUtils.readPositiveBigDecimal("Enter amount to withdraw : ");
        if(AmountUtils.hasSufficientBalance(account.getBalance(),amount)){
            account.withdraw(amount);
            System.out.println("withdrawal of " + amount + "successful. New Balance : "+ account.getBalance());
        } else {
            System.out.println("insufficient balance");
            return;
        }

      String description = ConsoleUtils.readString("Enter description : ");
        Transaction transaction = new Transaction (account.getAccountId(), Transaction.Type.WITHDRAW,amount,account.getAccountId(),description );
        transactionRepository.save(transaction);
        return;
    }

    public void transfer(boolean isInner){
        Account account1 = AccountService.getAccount();
        if (account1 == null){
            return;

        }

        Account account2 = AccountService.getAccount();


    }



}
