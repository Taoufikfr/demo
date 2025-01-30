package com.example.demo.bank;


import java.util.*;

public class Account {
    private List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    public void deposit(int amount, String date) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount, date, balance));
    }

    public void withdraw(int amount, String date) {
        balance -= amount;
        transactions.add(new Transaction("withdrawal", amount, date, balance));
    }

    public List<Transaction> getTransactions() {
        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());
        return transactions;
    }

    public int getBalance() {
        return balance;
    }
}
