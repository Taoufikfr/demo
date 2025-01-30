package com.example.demo.bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
    }

    @Test
    public void testDeposit() {
        // Arrange
        int depositAmount = 1000;
        account.deposit(depositAmount, "10-01-2012");

        // Act
        List<Transaction> transactions = account.getTransactions();

        // Assert
        assertEquals(1, transactions.size());
        assertEquals(depositAmount, transactions.get(0).getAmount());
        assertEquals("deposit", transactions.get(0).getType());
    }

    @Test
    public void testWithdraw() {
        // Arrange
        account.deposit(1000, "10-01-2012");
        int withdrawAmount = 500;

        // Act
        account.withdraw(withdrawAmount, "14-01-2012");

        // Assert
        List<Transaction> transactions = account.getTransactions();
        assertEquals(2, transactions.size());
        assertEquals(withdrawAmount, transactions.get(1).getAmount());
        assertEquals("withdrawal", transactions.get(1).getType());
    }

    @Test
    public void testGetStatement() {
        // Arrange
        account.deposit(1000, "10-01-2012");
        account.deposit(2000, "13-01-2012");
        account.withdraw(500, "14-01-2012");

        // Act
        List<Transaction> statement = account.getTransactions();

        // Assert
        assertEquals(3, statement.size());
        assertEquals("14-01-2012", statement.get(0).getDate());
        assertEquals("13-01-2012", statement.get(1).getDate());
        assertEquals("10-01-2012", statement.get(2).getDate());
    }
}
