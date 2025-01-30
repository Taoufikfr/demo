package com.example.demo.bank;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final Account account = new Account();

    @PostMapping("/deposit")
    public void deposit(@RequestParam int amount, @RequestParam String date) {
        account.deposit(amount, date);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam int amount, @RequestParam String date) {
        account.withdraw(amount, date);
    }

    @GetMapping("/statement")
    public List<Transaction> getStatement() {
        return account.getTransactions();
    }

    @GetMapping("/balance")
    public int getBalance() {
        return account.getBalance();
    }
}
