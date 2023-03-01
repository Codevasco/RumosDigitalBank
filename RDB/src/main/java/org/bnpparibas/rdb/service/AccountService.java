package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface AccountService {

    List<Account> findAllAccounts();

    ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    ResponseEntity<Object> addAccount(Account account, Long fiscalNumber, Date dateOfBirth);

    ResponseEntity<Object> deleteAccount(Long accountNumber);
}