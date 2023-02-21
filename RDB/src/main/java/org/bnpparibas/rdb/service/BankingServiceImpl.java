package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.domain.AccountDomain;
import org.bnpparibas.rdb.domain.ClientDomain;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
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
    public List<ClientDomain> findAllClients() {

        List<ClientDomain> clients = new ArrayList<>();
        Iterable<Client> clientList = clientRepository.findAll();

        clientList.forEach(client -> {
            clients.add(bankingBuilder.convertToClientDomain(client));
        });

        return clients;
    }

    /**
     * Finds client by fiscal number (NIF)
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> findByNif(Long nif) {

        Optional<Client> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToClientDomain(clientOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
    }

    /**
     * Creates a new client
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> addClient(ClientDomain clientDomain, Long nif) {

        Optional<Client> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isEmpty()) {
            Client client = bankingBuilder.convertToClientEntity(clientDomain);
            client.setClientCreationDate(new Date());
            clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("New client created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client already exists.");
        }
    }

    /**
     * Updates client details
     */
    @Override // TODO METHOD
    public ResponseEntity<Object> updateClient(Client client, Long nif) {
        return null;
    }

    /**
     * Deletes an existing client
     */
    @Override
    public ResponseEntity<Object> deleteClient(Long nif) {

        Optional<Client> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isPresent()) {
            Client existingClient = clientOptional.get();
            clientRepository.delete(existingClient);
            return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist.");
        }
    }

    /**
     * Returns a list of all existing accounts
     */
    @Override // TODO CHECK
    public List<AccountDomain> findAllAccounts() {

        List<AccountDomain> accounts = new ArrayList<>();
        Iterable<Account> accountList = accountRepository.findAll();

        accountList.forEach(account -> {
            accounts.add(bankingBuilder.convertToAccountDomain(account));
        });

        return accounts;
    }

    /**
     * Finds account by account number
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToAccountDomain(accountOptional.get()));

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account " + accountNumber + " does not exist.");
        }
    }

    /**
     * Creates a new account
     */
    @Override // TODO CHECK
    public ResponseEntity<Object> addAccount(AccountDomain accountDomain, Long nif) {

        Optional<Client> clientOptional = clientRepository.findByNif(nif);

        if (clientOptional.isPresent()) {
            accountRepository.save(bankingBuilder.convertToAccountEntity(accountDomain));
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist");
        }
    }

    /**
     * Updates account details
     */
    @Override // TODO METHOD
    public ResponseEntity<Object> updateAccount(Account account, Long nif) {
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
    public List<Transaction> findTransanctionsByAccountNumber(Long accountNumber) {
        return null;
    }
}