package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.TransactionEntity;
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

    @GetMapping(path = "/account-directory")
    public List<Account> getAllAccounts() {
        return bankingService.findAllAccounts();
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Object> getFindByAccountNumber(@PathVariable Long accountNumber) {
        return bankingService.findByAccountNumber(accountNumber);
    }

    @PostMapping("/add/{fiscalNumber}")
    public ResponseEntity<Object> getAddAccount(@RequestBody Account account, @PathVariable Long fiscalNumber) {
        return bankingService.addAccount(account, fiscalNumber);
    }

    @DeleteMapping(path = "/delete/{accountNumber}")
    public ResponseEntity<Object> getDeleteAccount(@PathVariable Long accountNumber) {
        return bankingService.deleteAccount(accountNumber);
    }

    @GetMapping("/transaction/{accountNumber}")
    public List<TransactionEntity> getFindTransactionsByAccountNumber(@PathVariable Long accountNumber) {
        return bankingService.findTransanctionsByAccountNumber(accountNumber);
    }
}