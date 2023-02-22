package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.bnpparibas.rdb.repository.*;
import org.bnpparibas.rdb.service.builder.BankingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BankingServiceImpl implements BankingService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankingBuilder bankingBuilder;

    public BankingServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Returns a list of all existing clients
     */
    @Override // TODO CHECK
    public List<Client> findAllClients() {

        List<Client> clients = new ArrayList<>();
        Iterable<ClientEntity> clientList = clientRepository.findAll();

        clientList.forEach(client -> {
            clients.add(bankingBuilder.convertToClientEntity(client));
        });

        return clients;
    }

    /**
     * Finds client by fiscal number (NIF)
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> findByNif(Long nif) {

        Optional<ClientEntity> clientOptional = clientRepository.findByNif(nif);

        return clientOptional.<ResponseEntity<Object>>map(clientEntity ->
                ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToClientEntity(clientEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found."));
    }

    /**
     * Creates a new client
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> addClient(Client client, Long nif) {

        Optional<ClientEntity> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isEmpty()) {
            ClientEntity clientEntity = bankingBuilder.convertToClientModel(client);
            clientEntity.setClientCreationDate(new Date());
            clientRepository.save(clientEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("New client created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client already exists.");
        }
    }

    /**
     * Updates client details
     */
    @Override // TODO METHOD
    public ResponseEntity<Object> updateClient(ClientEntity clientEntity, Long nif) {
        return null;
    }

    /**
     * Deletes an existing client
     */
    @Override
    public ResponseEntity<Object> deleteClient(Long nif) {

        Optional<ClientEntity> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isPresent()) {
            ClientEntity existingClientEntity = clientOptional.get();
            clientRepository.delete(existingClientEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist.");
        }
    }

    /**
     * Returns a list of all existing accounts
     */
    @Override // TODO CHECK
    public List<Account> findAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        Iterable<AccountEntity> accountList = accountRepository.findAll();

        accountList.forEach(account -> {
            accounts.add(bankingBuilder.convertToAccountEntity(account));
        });

        return accounts;
    }

    /**
     * Finds account by account number
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {

        Optional<AccountEntity> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        return accountOptional.<ResponseEntity<Object>>map(accountEntity ->
                ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToAccountEntity(accountEntity)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account " + accountNumber + " does not exist."));
    }

    /**
     * Creates a new account
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> addAccount(Account account, Long nif) {

        Optional<ClientEntity> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isPresent()) {
            accountRepository.save(bankingBuilder.convertToAccountModel(account));
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist");
        }
    }

    /**
     * Updates account details
     */
    @Override // TODO METHOD
    public ResponseEntity<Object> updateAccount(AccountEntity account, Long nif) {
        return null;
    }

    /**
     * Deletes an existing account
     */
    @Override // TODO METHOD
    public ResponseEntity<Object> deleteAccount(Long accountNumber) {
        return null;
    }

    /**
     * Shows the transaction history for a specified account
     */
    @Override // TODO METHOD
    public List<TransactionEntity> findTransanctionsByAccountNumber(Long accountNumber) {
        return null;
    }
}