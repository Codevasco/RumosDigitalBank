package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    ResponseEntity<Object> deposit(Deposit deposit, Long fiscalNumber);

    ResponseEntity<Object> withdraw(Withdrawal withdrawal, Long fiscalNumber);

    ResponseEntity<Object> transfer(Transfer transfer, Long fiscalNumber);

    List<Transaction> findTransanctionsByAccountNumber(Integer accountNumber);
}