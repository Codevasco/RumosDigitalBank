package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.service.AccountService;
import org.bnpparibas.rdb.service.implementation.AccountServiceImpl;
import org.bnpparibas.rdb.service.implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() { // TODO \ POSTMAN USE-CASE
        return accountService.findAllAccounts();
    }

    @GetMapping("/findAccount")
    public ResponseEntity<Object> getFindByAccountNumber(@RequestParam Long accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Object> postAddAccount(@RequestBody Account account, Long fiscalNumber) {
        return accountService.addAccount(account, fiscalNumber);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<Object> deleteAccount(@RequestParam Long accountNumber) {
        return accountService.deleteAccount(accountNumber);
    }
}