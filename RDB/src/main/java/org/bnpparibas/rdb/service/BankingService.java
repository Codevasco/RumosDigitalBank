package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    public List<Client> findAll();

    public ResponseEntity<Object> addClient(Client client);

    public ResponseEntity<Object> findByNif(Long nif);

    public ResponseEntity<Object> updateClient(Client client, Long nif);

    public ResponseEntity<Object> deleteClient(Long nif);

    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    public ResponseEntity<Object> addAccount(Account account, Long nif);

    public List<Transaction> findTransanctionsByAccountNumber(Long accountNumber);

}