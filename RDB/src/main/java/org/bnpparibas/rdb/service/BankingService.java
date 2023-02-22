package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    public List<Client> findAllClients();

    public ResponseEntity<Object> findByNif(Long nif);

    public ResponseEntity<Object> addClient(Client client, Long nif);

    public ResponseEntity<Object> updateClient(ClientEntity clientEntity, Long nif);

    public ResponseEntity<Object> deleteClient(Long nif);

    public List<Account> findAllAccounts();

    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    public ResponseEntity<Object> addAccount(Account account, Long nif);

    public ResponseEntity<Object> updateAccount(AccountEntity account, Long nif);

    public ResponseEntity<Object> deleteAccount(Long accountNumber);

    public List<TransactionEntity> findTransanctionsByAccountNumber(Long accountNumber);

}