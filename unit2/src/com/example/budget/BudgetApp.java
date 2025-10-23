package com.example.budget;
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


        // here I'm telling Java I have a local variable named openingBalance, I have not yet given it a value.
        //
        double openingBalance;
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

        BankAccount account = new BankAccount(openingBalance, holder);
        System.out.println("£" + account.getAccountDetails());


        boolean running = true;
        while (running) {
            System.out.println("==================================");
            System.out.println("Welcome to Budget Manager CLI");
            System.out.println("===================================\n");
            System.out.println("£" +account.getBalance());

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
                showDepositScreen(scanner, account);
            } else if (choice == 2) {
                showWithdrawalScreen(scanner, account);
            } else if (choice == 3) {
                showBalanceScreen(scanner, account);
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
    public static void showDepositScreen(Scanner scanner, BankAccount account) {
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
                System.out.println("Your new balance is: £%.2f%n" + newBal);
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


    public static void showWithdrawalScreen(Scanner scanner, BankAccount account) {
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

    public static void showBalanceScreen(Scanner scanner, BankAccount account) {
        System.out.println(account.getAccountDetails());
        System.out.println("Press Enter to return to the main menu..");
        scanner.nextLine();
    }

    public static void showExitScreen() {
        System.out.println("----Exit----");
    }
}
