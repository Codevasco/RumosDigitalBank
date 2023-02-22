package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    List<Client> findAllClients(); // TODO Test Use-Case  - POSTMAN

    ResponseEntity<Object> findByNif(Long nif);

    ResponseEntity<Object> addClient(Client client, Long nif);

    ResponseEntity<Object> updateClient(ClientEntity clientEntity, Long nif);

    ResponseEntity<Object> deleteClient(Long nif);

    List<Account> findAllAccounts(); // TODO Test Use-Case - POSTMAN

    ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    ResponseEntity<Object> addAccount(Account account, Long nif);

    ResponseEntity<Object> updateAccount(AccountEntity account, Long nif);

    ResponseEntity<Object> deleteAccount(Long accountNumber);

    List<TransactionEntity> findTransanctionsByAccountNumber(Long accountNumber);
}