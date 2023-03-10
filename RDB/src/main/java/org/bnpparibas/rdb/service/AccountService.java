package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface AccountService {

    List<Account> findAllAccounts(Long fiscalNumber);

    ResponseEntity<Object> findByAccountNumber(Integer accountNumber);

    ResponseEntity<Object> addAccount(Account account, Long fiscalNumber);

    ResponseEntity<Object> updateAccount(Account account, Integer accountNumber);

    ResponseEntity<Object> deleteAccount(Integer accountNumber);
}