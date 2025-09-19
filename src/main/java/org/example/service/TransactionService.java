package org.example.service;

import org.example.model.Account;
import org.example.model.Transaction;
import org.example.repository.AccountRepository;
import org.example.repository.TransactionRepository;
import org.example.utils.AmountUtils;
import org.example.utils.ConsoleUtils;

import java.math.BigDecimal;
import java.util.List;

import static org.example.model.Transaction.Type.DEPOSIT;

public class TransactionService {

    public static TransactionRepository transactionRepository;
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

        Account account2 = null;
        String outerAccountRib = null;
        if (isInner){
            account2 = AccountService.getAccount();
            if(account1 == account2){
                System.out.println("Cannot transfer to the same account");
                return;
            }
        }else {
            outerAccountRib = ConsoleUtils.readString("Enter outer account Rib : ");
        }
        BigDecimal amount = ConsoleUtils.readPositiveBigDecimal("Transfer amount: ");
        if(!AmountUtils.hasSufficientBalance(account1.getBalance(),amount)){
            System.out.println("insufficient balance");
            return;
        }
        String description = ConsoleUtils.readString("Enter description : ");

        if(isInner){
            account1.transfer(amount,account2);
            System.out.println("Transfer successful. New Balance : "+ account1.getBalance());
        }else {
            account1.transfer(amount, outerAccountRib);
            System.out.println("Transfer successful. New Balance : "+ account1.getBalance());
        }

        Transaction transaction1;
        Transaction transaction2 = null;

        if (isInner){

            transaction1 = new Transaction(
                    account1.getAccountId(),
                    Transaction.Type.TRANSFEROUT,
                    amount,
                    account2.getAccountId(),
                    description
            );
            transaction2 = new Transaction(
                    account2.getAccountId(),
                    Transaction.Type.TRANSFERIN,
                    amount,
                    account1.getAccountId(),
                    description
            );

        }else{
            transaction1 = new Transaction(
                    account1.getAccountId(),
                    Transaction.Type.TRANSFEROUT,
                    amount,
                    outerAccountRib,
                    description
            );
        }

        transactionRepository.save(transaction1);
        if(isInner){
            transactionRepository.save(transaction2);
        }

    }

    public static void listTransactions(){
        Account account = AccountService.getAccount();
        if (account == null) {
            return;
        }
        else{
            System.out.println("Transactions for account "+account.getAccountId()+"|"+account.getType());
            System.out.println("---------------------------------------");
            List<Transaction> transactions=transactionRepository.findByAccountId(account.getAccountId());
            for (Transaction transaction : transactions) {
                System.out.println(transaction.toString());
            }
        }
    }



}
