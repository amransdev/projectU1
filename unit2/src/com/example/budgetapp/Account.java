package com.example.budgetapp;

// Created this interface so the menu can talk to the interface instead of the specific class

public interface Account {
    String getAccountDetails();
    String getAccountHolder();
    double getBalance();
    double depositAmount(double amount);
    double withdrawAmount(double amount);

    default void applyMonthlyInterest() {}
}
