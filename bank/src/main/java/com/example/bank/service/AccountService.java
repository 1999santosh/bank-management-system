package com.example.bank.service;

import com.example.bank.entity.Account;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(Account account) {
        if (accountRepository.findByAccountNumber(account.getAccountNumber()) != null) {
            throw new IllegalArgumentException("Account number already exists");
        }
    
        return accountRepository.save(account);
    }
    
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
    public void deleteAccount(Long id) {
        if(id==null)
        {
            throw new  RuntimeException(" Id not found...!");
        }
        accountRepository.deleteById(id);
    }

    public Account deposit(String accountNumber, double amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account==null) 
        {
            throw new RuntimeException("account not found...!");
        }
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(String accountNumber, double amount) {
        System.out.println("Withdraw requested for: " + accountNumber + ", Amount: " + amount);
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if(account==null)
        {
            throw new RuntimeException("account not found...!"+accountNumber);
        }
        if(account.getBalance()<amount)
        {
            throw new RuntimeException("Insufficent balance...!");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    public Account updateAccount(Long id, Account updatedAccount) {
        Account existing = getAccountById(id);
        if (existing != null) {
            existing.setName(updatedAccount.getName());
            existing.setEmail(updatedAccount.getEmail());
            existing.setBalance(updatedAccount.getBalance());
            return accountRepository.save(existing);
        }
        return null;
    }

}

