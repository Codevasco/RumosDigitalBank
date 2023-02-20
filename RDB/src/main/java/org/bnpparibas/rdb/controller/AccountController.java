package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping("/{nif}")
    public ResponseEntity<Object> getFindByNif(@PathVariable Long nif) {
        return bankingService.findByNif(nif);
    }

    @PostMapping("/add/{nif}")
    public ResponseEntity<Object> getAddAccount(@RequestBody Account account, @PathVariable Long nif) {
        return bankingService.addAccount(account, nif);
    }

    @GetMapping("/transaction/{nif}")
    public List<Transaction> getFindTransactionByAccountNumber(@PathVariable Long accountNumber) {
        return bankingService.findTransanctionsByAccountNumber(accountNumber);
    }


}