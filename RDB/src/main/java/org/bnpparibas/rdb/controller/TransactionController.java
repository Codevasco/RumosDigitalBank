package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.bnpparibas.rdb.service.implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Object> postDeposit(@RequestBody Deposit deposit, Long accountNumber) {
        return transactionService.deposit(deposit, accountNumber);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Object> postWithdrawal(@RequestBody Withdrawal withdrawal, Long accountNumber) {
        return transactionService.withdraw(withdrawal, accountNumber);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> postTransfer(@RequestBody Transfer transfer, Long accountNumber) {
        return transactionService.transfer(transfer, accountNumber);
    }

    @GetMapping("/transactions")
    public List<Transaction> getFindTransactionsByAccountNumber(@RequestParam Long accountNumber) {
        return transactionService.findTransanctionsByAccountNumber(accountNumber);
    }
}