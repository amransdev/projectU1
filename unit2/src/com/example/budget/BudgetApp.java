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
            showInitialBalance(balance);


            System.out.println("Main Menu");
            System.out.println("----------");

            System.out.println("1) Deposit amount");
            System.out.println("2) Withdraw amount");
            System.out.println("3) Check balance");
            System.out.println("4) Exit");

            System.out.println("Choose an option 1-4");
            String choiceText = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number 1-4.");
                continue;
            }


            // Handling choice section
            if (choice == 1) {
                balance = showDepositScreen(scanner, balance);
            } else if (choice == 2) {
                balance = showWithDrawalScreen(scanner, balance);
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
    public static double showDepositScreen(Scanner scanner, double balance) {
        while (true) {
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
                    balance = balance + amount;
                    System.out.println("Successfully deposited ");
                    System.out.println("Your new balance is: " + "£" + balance);
                    System.out.println("Press Enter to return to the main menu...");
                    scanner.nextLine();
                    return balance;
                }

                // handle invalid input section
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g., 12.50).");
            }
        }
    }


    public static double showWithDrawalScreen(Scanner scanner, double balance) {
        while (true) {
            System.out.println("Please enter an amount to withdraw (or 'b' to go back):");
            String input = scanner.nextLine().trim();

            // back option
            if (input.equalsIgnoreCase("b")) {
                return balance;
            }

            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0) {
                    System.out.println("Amount must be positive. ");
                } else if (amount > balance) {
                    System.out.println("Insufficient funds for this withdrawal. Current balance is: " + "£" + balance + ".");
                } else {
                    balance = balance - amount;
                    System.out.println("Successfully withdrawn!. ");
                    System.out.println("Your new balance is: " + "£" + balance);
                    return balance; // exit after successful withdrawal
                }


            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

        public static double showBalanceScreen (Scanner scanner,double balance){
            System.out.println("Your current balance is: £" + balance);
            System.out.println("Press Enter to return to the main menu...");
            scanner.nextLine();
            return balance;
        }

        public static void showExitScreen(){
            System.out.println("----Exit----");
        }

        public static void showInitialBalance(double balance) {
            System.out.println("Initial balance: £" + balance);
        }
 }


