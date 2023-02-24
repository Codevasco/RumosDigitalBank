package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.springframework.http.ResponseEntity;

public interface OperationsService {

    ResponseEntity<Object> deposit(Deposit deposit, Long fiscalNumber);

    ResponseEntity<Object> withdraw(Withdrawal withdraw, Long fiscalNumber);

    ResponseEntity<Object> transfer(Transfer transfer, Long fiscalNumber);
}