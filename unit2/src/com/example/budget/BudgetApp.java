package com.example.budget;

import java.util.Scanner;

public class BudgetApp {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean running = true;

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
                showDepositScreen(scanner);
            } else if (choice == 2) {
                showWithDrawalScreen(scanner);
            } else if (choice == 3) {
                showCheckBalanceScreen(scanner);
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

        static void showDepositScreen(Scanner scanner) {
            System.out.println("Please enter an amount to deposit: ");
            scanner.nextLine();
        }


        static void showWithDrawalScreen (Scanner scanner) {
            System.out.println("Please enter an amount to withdraw:");
            scanner.nextLine();
        }


        static void showCheckBalanceScreen (Scanner scanner) {
            System.out.println("Your current balance is:  ");
            scanner.nextLine();
        }


        static void showExitScreen (Scanner scanner) {
            System.out.println("----Exit----");
        }


    }
