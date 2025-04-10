package com.example.bank.controller;

import com.example.bank.entity.Account;
import com.example.bank.service.AccountService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

   @PostMapping
   public ResponseEntity<?> createAccount(@Valid @RequestBody Account account, BindingResult result) {
       if (result.hasErrors()) {
           List<String> errors = result.getFieldErrors().stream()
                   .map(e -> e.getField() + ": " + e.getDefaultMessage())
                   .collect(Collectors.toList());
           return ResponseEntity.badRequest().body(errors);
       }
   
       Account created = accountService.createAccount(account);
       return ResponseEntity.ok(created);
   }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAllAccounts();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @PostMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return accountService.deposit(accountNumber, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }


}

