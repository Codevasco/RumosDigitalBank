package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    private OperationsServiceImpl operationsService;

    @PostMapping("/deposit")
    public ResponseEntity<Object> getDeposit(@RequestBody Deposit deposit, Long accountNumber) {
        return operationsService.deposit(deposit, accountNumber);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<Object> getDeposit(@RequestBody Withdrawal withdrawal, Long accountNumber) {
        return operationsService.withdraw(withdrawal, accountNumber);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Object> getDeposit(@RequestBody Transfer transfer, Long accountNumber) {
        return operationsService.transfer(transfer, accountNumber);
    }
}