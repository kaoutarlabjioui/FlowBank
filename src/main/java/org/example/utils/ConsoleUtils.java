package org.example.utils;

import java.util.Scanner;

public class ConsoleUtils {
    private static final Scanner scanner =  new Scanner(System.in);

    public static String readString(String prompt) {

        String input ;
        while (true){
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()){
                return break;
            }

            System.out.println("Input cannot be empty ");
        }
        return input;



    }
}
