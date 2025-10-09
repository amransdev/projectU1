package com.example.budget;

import java.util.Scanner;

public class BudgetApp {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        double balance = 0.0;

        while (running) {
            System.out.println("==================================");
            System.out.println("Welcome to Budget Manager CLI");
            System.out.println("===================================\n");

            System.out.println("Main Menu");
            System.out.println("----------");

            System.out.println("1) Deposit amount");
            System.out.println("2) Withdraw amount");
            System.out.println("3) Check balance");
            System.out.println("4) Exit");

            System.out.println("Choose an option 1-4");
            int choice = scanner.nextInt();
            scanner.nextLine();



            // Handling choice section
            if (choice == 1) {
                balance = showDepositScreen(scanner, balance);
            } else if (choice == 2) {
                showWithDrawalScreen(scanner, balance);
            } else if (choice == 3) {
                showBalanceScreen(scanner, balance);
            } else if (choice == 4) {
                System.out.println("Thank you for using our budget manager!");
                running = false;

            } else {
                System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
    }






    // helpers
    static double showDepositScreen(Scanner scanner, double balance) {
        System.out.println("Please enter an amount to deposit (or 'b' to go back): ");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("b")) {
            return balance;
        }

// below try converts the users input into a number.
       try {
           double amount = Double.parseDouble(input);
           if (amount <= 0) {
               System.out.println("Please enter a positive number.");
           } else {
               balance += amount;
               System.out.println("Successfully deposited. Your new balance is: " + balance);
           }

            // handle invalid input section
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number (e.g., 12.50).");
        }
        System.out.println("Press Enter to return to the main menu.");
        scanner.nextLine();

        return balance;
        }







        static void showWithDrawalScreen(Scanner scanner, double balance) {
            System.out.println("Please enter an amount to withdraw:");
            scanner.nextLine();
        }








        static void showBalanceScreen (Scanner scanner, double balance) {
            System.out.println("Your current balance is:  ");
            scanner.nextLine();
        }







        static void showExitScreen (Scanner scanner) {
            System.out.println("----Exit----");

    }










}

