package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.domain.AccountDomain;
import org.bnpparibas.rdb.domain.ClientDomain;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    public List<ClientDomain> findAllClients();

    public ResponseEntity<Object> findByNif(Long nif);

    public ResponseEntity<Object> addClient(ClientDomain clientDomain, Long nif);

    public ResponseEntity<Object> updateClient(Client client, Long nif);

    public ResponseEntity<Object> deleteClient(Long nif);

    public List<AccountDomain> findAllAccounts();

    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    public ResponseEntity<Object> addAccount(AccountDomain accountDomain, Long nif);

    public ResponseEntity<Object> updateAccount(Account account, Long nif);

    public ResponseEntity<Object> deleteAccount(Long accountNumber);

    public List<Transaction> findTransanctionsByAccountNumber(Long accountNumber);

}