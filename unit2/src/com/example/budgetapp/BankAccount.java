package com.example.budgetapp;

public class BankAccount implements Account {
    private double balance;
    private String accountHolder;
    private static long NEXT_ID = 1;
    private long id;


    public BankAccount(double initialBalance, String accountHolder) {

        if (accountHolder == null || accountHolder.isBlank())
            throw new IllegalArgumentException("You must enter a name");
        if (initialBalance < 0)
            throw new IllegalArgumentException("Your initial balance must be positive");
        this.balance = initialBalance;
        this.accountHolder = accountHolder.trim();
        this.id = NEXT_ID++;
    }



    public String getAccountDetails() {
        return String.format(
                "ID: %d%nAccount Holder: %s%nBalance: £%.2f",
                id, accountHolder, balance
        );
    }


    public String getAccountHolder() {
        return this.accountHolder;
    }


    public double getBalance() {
        return this.balance;
    }


    public double depositAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be a positive number");
        }
        balance += amount;
        return balance;
    }

    public double withdrawAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds. Current balance: £" + balance);
        }
        balance -= amount;
        return balance;
    }


}
