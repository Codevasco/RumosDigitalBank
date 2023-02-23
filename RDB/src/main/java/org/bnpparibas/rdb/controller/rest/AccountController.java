package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController { // TODO POSTMAN

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping("/accountDirectory")
    public List<Account> getAllAccounts() { // TODO \ POSTMAN USE-CASE
        return bankingService.findAllAccounts();
    }

    @GetMapping("/findAccount")
    public ResponseEntity<Object> getFindByAccountNumber(@PathVariable Long accountNumber) {
        return bankingService.findByAccountNumber(accountNumber);
    }

    @PostMapping("/addAccount")
    public ResponseEntity<Object> postAddAccount(@RequestBody Account account, @PathVariable Long fiscalNumber) {
        return bankingService.addAccount(account, fiscalNumber);
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long accountNumber) {
        return bankingService.deleteAccount(accountNumber);
    }

    @GetMapping("/transactionAccount")
    public List<Transaction> getFindTransactionsByAccountNumber(@PathVariable Long accountNumber) {
        return bankingService.findTransanctionsByAccountNumber(accountNumber);
    }
}