package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.domain.AccountDomain;
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
    public ResponseEntity<Object> getAddAccount(@RequestBody AccountDomain accountDomain, @PathVariable Long nif) {
        return bankingService.addAccount(accountDomain, nif);
    }

    @GetMapping("/transaction/{accountNumber}")
    public List<Transaction> getFindTransactionsByAccountNumber(@PathVariable Long accountNumber) {
        return bankingService.findTransanctionsByAccountNumber(accountNumber);
    }
}