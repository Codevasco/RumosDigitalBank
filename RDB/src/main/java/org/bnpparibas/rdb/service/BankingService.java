package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    List<Client> findAllClients();

    ResponseEntity<Object> findByFiscalNumber(Long fiscalNumber);

    ResponseEntity<Object> addClient(Client client, Long fiscalNumber);

    ResponseEntity<Object> updateClient(ClientEntity clientEntity, Long fiscalNumber);

    ResponseEntity<Object> deleteClient(Long fiscalNumber);

    List<Account> findAllAccounts();

    ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    ResponseEntity<Object> addAccount(Account account, Long fiscalNumber);

    ResponseEntity<Object> updateAccount(AccountEntity account, Long fiscalNumber);

    ResponseEntity<Object> deleteAccount(Long accountNumber);

    List<TransactionEntity> findTransanctionsByAccountNumber(Long accountNumber);
}