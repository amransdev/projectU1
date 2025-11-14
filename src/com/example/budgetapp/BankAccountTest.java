package com.example.budgetapp;
import com.example.budgetapp.domain.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {


    @Test

    void depositShouldIncreaseBalance() {
        BankAccount account = new BankAccount(100, "Amran");

        double newBalance = account.depositAmount(200);

        assertEquals(300.0, newBalance);
    }

}