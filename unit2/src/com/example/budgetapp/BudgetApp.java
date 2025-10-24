package com.example.budgetapp;
import java.util.Scanner;


public class BudgetApp {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account holder name: ");
        String holder = scanner.nextLine().trim();

        while (holder.isBlank()) {
            System.out.println("Name cannot be blank. Enter account holder name");
            holder = scanner.nextLine().trim();
        }

        System.out.println("choose account type: ");
        System.out.println("1) Standard (BankAccount)");
        System.out.println("2) SavingsAccount");
        int accountType = 0; // Declares a variable to hold a users' choice.

        // below is an input loop that keeps running until the user enters 1 or 2.
        while (true) {
            String t = scanner.nextLine().trim(); // this line reads the whole line the user has typed and strips spaces.
            try {
                accountType = Integer.parseInt(t); // this line converts the string to a number
                if (accountType == 1 || accountType == 2) break;
            } catch (NumberFormatException ignored) {}
            /* NFE is thrown if the text isn't a valid integer. The NFE is empty {} so that if the user
            types in something that is not an integer, the exception is swallowed and the program continues to
            the next line (which re-prompts with "please enter 1 or 2") */
            System.out.println("Please enter 1 or 2:");
        }

        double annualRate = 0.0;
        if (accountType == 2) {
            while (true) {
                System.out.println("Enter annual interest rate as a decimal (e.g. 0.03 for 3%):");
                String r = scanner.nextLine().trim();
                try {
                    annualRate = Double.parseDouble(r); // This is a static method from the Java Double class, it takes string like "0.03" and converts it to a  double number (0.03).
                    if (annualRate < 0) {
                        System.out.println("Rate cannot be negative");
                        continue; // this means "skip everything else in this loop and start the next round immediately" - so jumps back to the top of the loop and program asks again to enter interest rate.
                    }
                    break; // this means STOP the loop completely and move on - if the user types something valid it passes the check, program reaches break, the loop ends
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid decimal number (e.g., 0.02)."); // so because there's no break in the catch, the loop automatically starts over again.
                }
            }
        }


        double openingBalance; // this is declaring a variable and telling java 'i'll be using this variable later on to store a double'
        // the above variable is used outside of the loop so that it can be used after the loop finishes.
        while (true) {
            System.out.println("Enter opening balance (e.g., 100.00): ");
            String input = scanner.nextLine().trim();
            try {
                openingBalance = Double.parseDouble(input);
                if (openingBalance < 0) {
                    System.out.println("Opening balance cannot be negative");
                    continue; // this jumps back to the top of the loop and asks again for the opening balance.
                }
                break; // means if it reaches here, the input was valid so break signals to stop looping and proceed to next line.
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g., 12.50).");
            }
        }

        Account account;
        if (accountType == 1) {
            account = new BankAccount(openingBalance, holder);
        } else {
            account = new SavingsAccount(openingBalance, holder, annualRate);
        }

        boolean running = true;
        while (running) {
            System.out.println("==================================");
            System.out.println("Welcome to Budget Manager CLI");
            System.out.println("===================================\n");
            System.out.printf("£%.2f%n", account.getBalance());

            System.out.println("Main Menu");
            System.out.println("----------");

            System.out.println("1) Deposit amount");
            System.out.println("2) Withdraw amount");
            System.out.println("3) Check balance");
            System.out.println("4) Exit");
            System.out.println("5) Apply monthly interest (Savings only)");

            System.out.println("Choose an option 1-5");
            String choiceText = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number 1-5.");
                continue;
            }


            // Handling choice section
            if (choice == 1) {
                showDepositScreen(scanner, account);
            } else if (choice == 2) {
                showWithdrawalScreen(scanner, account);
            } else if (choice == 3) {
                showBalanceScreen(scanner, account);
            } else if (choice == 4) {
                System.out.println("Thank you for using our budget manager!");
                running = false;
            } else if (choice == 5) {
                applyMonthlyInterest(scanner, account);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
}


    // helpers

    public static void applyMonthlyInterest(Scanner scanner, Account account) {
        account.applyMonthlyInterest(); // does nothing for BankAccount; applies for SavingsAccount
        System.out.printf("Done. New balance: £%.2f%n", account.getBalance());
        System.out.println("Press Enter to return to the main menu...");
        scanner.nextLine();
    }

    public static void showDepositScreen(Scanner scanner, Account account) {
        while (true) {
            System.out.println("Please enter an amount to deposit (or 'b' to go back): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("b")) {
                return;
            }
            // below try converts the users input into a number.
            try {
                double amount = Double.parseDouble(input);
                double newBal = account.depositAmount(amount);
                System.out.println("Successfully deposited.");
                System.out.printf("Your new balance is: £%.2f%n", newBal);
                System.out.println("Press Enter to return to the main menu...");
                scanner.nextLine();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g., 12.50).");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void showWithdrawalScreen(Scanner scanner, Account account) {
        while (true) {
            System.out.println("Please enter an amount to withdraw (or 'b' to go back):");
            String input = scanner.nextLine().trim();

            // back option
            if (input.equalsIgnoreCase("b")) {
                return;
            }

            try {
                // parse and withdraw
                double amount = Double.parseDouble(input);
                double newBal = account.withdrawAmount(amount);

                System.out.println("Successfully withdrawn.");
                System.out.println("Your new balance is: " + newBal);
                System.out.println("Press Enter to return to the main menu...");
                scanner.nextLine();
                return;

            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void showBalanceScreen(Scanner scanner, Account account) {
        System.out.println(account.getAccountDetails());
        System.out.println("Press Enter to return to the main menu..");
        scanner.nextLine();
    }

    public static void showExitScreen() {
        System.out.println("----Exit----");
    }
}
