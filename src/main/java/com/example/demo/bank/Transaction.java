package com.example.demo.bank;

public class Transaction {
    private String type;
    private int amount;
    private String date;
    private int balanceAfterTransaction;

    public Transaction(String type, int amount, String date, int balanceAfterTransaction) {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public int getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }
}
