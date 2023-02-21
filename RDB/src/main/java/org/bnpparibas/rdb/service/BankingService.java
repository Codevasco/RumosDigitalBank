package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.persistence.Account;
import org.bnpparibas.rdb.model.persistence.Client;
import org.bnpparibas.rdb.model.persistence.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    public List<Client> findAllClients();

    public ResponseEntity<Object> findByNif(Long nif);

    public ResponseEntity<Object> addClient(Client client);

    public ResponseEntity<Object> updateClient(Client client, Long nif);

    public ResponseEntity<Object> deleteClient(Long nif);

    public List<Account> findAllAccounts();

    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    public ResponseEntity<Object> addAccount(Account account, Long nif);

    public ResponseEntity<Object> updateAccount(Account account, Long nif);

    public ResponseEntity<Object> deleteAccount(Long accountNumber);

    public List<Transaction> findTransanctionsByAccountNumber(Long accountNumber);

}