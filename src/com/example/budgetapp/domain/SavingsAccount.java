package com.example.budgetapp.domain;

public class SavingsAccount extends BankAccount {
    double annualInterestRate;



    public SavingsAccount(double initialBalance, String accountHolder, double annualInterestRate) {
        super(initialBalance, accountHolder);
        if (annualInterestRate < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public void applyMonthlyInterest() {
        double monthlyRate = annualInterestRate / 12;
        double interest = getBalance() * monthlyRate;
        if (interest > 0) {
            depositAmount(interest);
        }

    }

    @Override
    public String getAccountDetails() {
        String parentDetails = super.getAccountDetails();
        String savingsDetails = String.format(
                "%nType: Savings%nAnnual Interest Rate: %.2f%%",
                annualInterestRate * 100.0
        );
        return parentDetails + savingsDetails;
    }



}
