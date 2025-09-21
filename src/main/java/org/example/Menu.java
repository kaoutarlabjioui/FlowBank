package org.example;

import org.example.repository.AccountRepository;
import org.example.repository.TransactionRepository;
import org.example.repository.impl.InMemoryAccountRepository;
import org.example.repository.impl.InMemoryTransactionRepository;
import org.example.service.AuthService;
import org.example.service.AccountService;
import org.example.service.TransactionService;
import org.example.utils.ConsoleColors;
import org.example.utils.ConsoleUtils;

import static org.example.service.AuthService.loggedInUser;

public class Menu {
    private static final AccountRepository accountRepository = new InMemoryAccountRepository();
    private static final AccountService accountService = new AccountService(accountRepository);
    private static final TransactionRepository transactionRepository = new InMemoryTransactionRepository();
    private static final TransactionService transactionService = new TransactionService(transactionRepository, accountRepository);

    public static boolean showLoginMenu() {
        System.out.println(ConsoleColors.CYAN + "\n=== LOGIN MENU ===" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "1. Register" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "2. Login" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "3. Exit" + ConsoleColors.RESET);

        int choice = ConsoleUtils.readInt("Enter choice: ", 1, 3);

        switch (choice) {
            case 1 -> System.out.println(AuthService.register());
            case 2 -> {


                    String result = AuthService.login();
                    System.out.println(result);
                }

            case 3 -> {
                return false; // exit app
            }
        }
        return true;
    }

    public static boolean showMainMenu() {
        System.out.println(ConsoleColors.CYAN + "\n=== MAIN MENU ===" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Logged in as: " + loggedInUser.getFullName() + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "1. Create Account" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "2. List My Accounts" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "3. Deposit" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "4. Withdraw" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "5. Transfer" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "6. Transaction History" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + "7. Change Password" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "8. Logout" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "9. Exit" + ConsoleColors.RESET);

        int choice = ConsoleUtils.readInt("Enter choice: ", 1, 9);

        switch (choice) {
            case 1 -> accountService.createAccount(loggedInUser.getEmail());
            case 2 -> accountService.listAccounts();
            case 3 -> transactionService.deposit();
            case 4 -> transactionService.withdraw();
            case 5 -> transferMenu();
            case 6 -> transactionService.listTransactions();
            case 7 -> AuthService.changePassword();
            case 8 -> {
                AuthService.logout();
                System.out.println(ConsoleColors.RED + "✅ Logged out." + ConsoleColors.RESET);
            }
            case 9 -> {
                return false; // exit app
            }
        }
        return true;
    }

    private static void transferMenu() {
        boolean back = false;

        while (!back) {
            System.out.println(ConsoleColors.CYAN + "\n=== TRANSFER MENU ===" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "1. Inner Transfer" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "2. Outer Transfer" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "3. Back" + ConsoleColors.RESET);

            int choice = ConsoleUtils.readInt("Enter choice: ", 1, 3);

            switch (choice) {
                case 1 -> transactionService.transfer(true);
                case 2 -> transactionService.transfer(false);
                case 3 -> back = true;
            }
        }
    }
}
