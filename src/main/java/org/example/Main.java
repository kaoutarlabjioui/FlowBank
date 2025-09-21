package org.example;

import org.example.service.AuthService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     💰 FLOWBANK - JAVA CONSOLE BANKING 💰");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            if (AuthService.loggedInUser == null) {
                running = Menu.showLoginMenu();
            } else {
                running = Menu.showMainMenu();
            }
        }

        System.out.println("👋 Thank you for using FlowBank. Goodbye!");
    }
}
