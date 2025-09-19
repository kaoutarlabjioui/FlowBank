package org.example.service;

import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepository;
import org.example.utils.ConsoleUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.example.model.Account.AccountType.*;

public class AccountService {

    public static AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(String ownerEmail) {
        if(AuthService.loggedInUser == null){
            System.out.println("User is not logged in");
            return;
        }
        User user = AuthService.loggedInUser;
        System.out.println("Creating account for user " + user.getEmail());
        System.out.println("Account Types:");
        System.out.println("1. standard");
        System.out.println("2. savings");
        System.out.println("3. credit");

        int choice = ConsoleUtils.readInt("Please chose the type of account you would like to create:",1,3);

        Account.AccountType accountType;
        switch (choice){
            case 1 -> accountType = Account.AccountType.standard;
            case 2 -> accountType = Account.AccountType.savings;
            case 3 -> accountType = Account.AccountType.credit;

            default -> {
                System.out.println("Invalid choice");

               return;
            }
        }
      Optional<Account> hasAccountType = accountRepository.checkUserAccountType(user,accountType);

        if(hasAccountType.isPresent()){
            System.out.println("Account already exists");
            return;
        }


    }

    public void listAccounts() {
        if(AuthService.loggedInUser == null){
            System.out.println("User is not logged in");
            return;
        }
        User user = AuthService.loggedInUser;
        List<Account> accounts = accountRepository.findByOwner(user.getId());

        if(accounts.isEmpty()){
            System.out.println("No accounts found");
            return;
        }

        System.out.println("Accounts for" + user.getFullName() + ":");

        for(Account acc : accounts){
            System.out.println("Account ID: " + acc.getAccountId()+" | Type: "+ acc.getType() + " | Balance: " + acc.getBalance());
        }
    }

   public void closeAccount(){
        Account account = getAccount();
         if(account == null){
             return;
         }
    if(!account.getBalance().equals(BigDecimal.ZERO)){
        System.out.println("Account cannot be closed. Balance must be zero.");
        return;
    }
       account.setActive(false);
    //accountRepository.delete(account);
       System.out.println("Account closed successfully.");
       return;
   }
    public static Account getAccount() {
        if(AuthService.loggedInUser==null){
            System.out.println("User is not logged in");
            return null;
        }
        User user=AuthService.loggedInUser;
        List<Account> userAccounts = accountRepository.findByOwner(user.getId());

        if (userAccounts.isEmpty()) {
            System.out.println("No accounts found for this user.");
            return null;
        }
        System.out.println("Select Account for " + user.getFullName() + " Deposit :");
        for(int i=0;i<userAccounts.size();i++){
            Account acc=userAccounts.get(i);
            System.out.println((i+1) + ") Account ID: " + acc.getAccountId()
                    + " | Type: " + acc.getType() + " | Balance: " + acc.getBalance());

        }
        int choice = ConsoleUtils.readInt("Pick an account: ", 1, userAccounts.size());
        return userAccounts.get(choice - 1);
    }



}
