package org.example;

import org.example.utils.ConsoleColors;

import java.util.Scanner;

public class Menu {

        public static void showMenu(boolean loggedIn, String userName) {
            System.out.println(ConsoleColors.CYAN + ConsoleColors.BOLD);
            System.out.println("========================================");
            System.out.println("     💰 FLOWBANK - JAVA CONSOLE 💰");
            System.out.println("========================================" + ConsoleColors.RESET);

            if(!loggedIn) {
                System.out.println(ConsoleColors.YELLOW + "1. Register" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "2. Login" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + "3. Exit" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.GREEN + "Logged in as: " + userName + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "1. Create Account" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "2. List My Accounts" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "3. Deposit" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "4. Withdraw" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "5. Transfer" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "6. Transaction History" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "7. Update Profile" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "8. Change Password" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + "9. Logout" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + "10. Exit" + ConsoleColors.RESET);
            }

            System.out.print(ConsoleColors.PURPLE + "Enter choice: " + ConsoleColors.RESET);
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            boolean loggedIn = false;
            String userName = "";

            while(true) {
                showMenu(loggedIn, userName);
                int choice = Integer.parseInt(sc.nextLine());

                if(!loggedIn) {
                    switch(choice) {
                        case 1 -> System.out.println(ConsoleColors.GREEN + "Register selected" + ConsoleColors.RESET);
                        case 2 -> {
                            System.out.println(ConsoleColors.GREEN + "Login successful" + ConsoleColors.RESET);
                            loggedIn = true;
                            userName = "Alice"; // exemple
                        }
                        case 3 -> { System.out.println(ConsoleColors.RED + "Bye!" + ConsoleColors.RESET); return; }
                        default -> System.out.println(ConsoleColors.RED + "Invalid choice!" + ConsoleColors.RESET);
                    }
                } else {
                    switch(choice) {
                        case 1 -> System.out.println("Create Account");
                        case 2 -> System.out.println("List Accounts");
                        case 3 -> System.out.println("Deposit");
                        case 4 -> System.out.println("Withdraw");
                        case 5 -> System.out.println("Transfer");
                        case 6 -> System.out.println("Transaction History");
                        case 7 -> System.out.println("Update Profile");
                        case 8 -> System.out.println("Change Password");
                        case 9 -> { loggedIn = false; userName = ""; System.out.println("Logged out"); }
                        case 10 -> { System.out.println("Bye!"); return; }
                        default -> System.out.println(ConsoleColors.RED + "Invalid choice!" + ConsoleColors.RESET);
                    }
                }
            }
        }



}
