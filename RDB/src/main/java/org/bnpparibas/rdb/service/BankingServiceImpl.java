package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.CardEntity;
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
    private CardRepository cardRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankingBuilder bankingBuilder;


    /**
     * Returns a list of all existing clients
     */
    @Override
    public List<Client> findAllClients() {

        List<Client> clients = new ArrayList<>();
        Iterable<ClientEntity> clientList = clientRepository.findAll();

        clientList.forEach(client -> {
            clients.add(bankingBuilder.convertToClientModel(client));
        });

        return clients;
    }

    /**
     * Finds client by fiscal number (NIF)
     */
    @Override
    public ResponseEntity<Object> findByFiscalNumber(Long fiscalNumber) {

        Optional<ClientEntity> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        return clientOptional.<ResponseEntity<Object>>map(client ->
                        ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToClientModel(client)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found."));
    }

    /**
     * Creates a new client
     */
    @Override
    public ResponseEntity<Object> addClient(Client client, Long fiscalNumber) {

        Optional<ClientEntity> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isEmpty()) {

            ClientEntity clientEntity = bankingBuilder.convertToClientEntity(client);
            clientRepository.save(clientEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body("New client created successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client already exists.");
        }
    }

    /**
     * Updates client details
     */
    @Override
    public ResponseEntity<Object> updateClient(Client client, Long fiscalNumber) {

        Optional<ClientEntity> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);
        ClientEntity clientEntity = bankingBuilder.convertToClientEntity(client);

        if (clientOptional.isPresent()) {
            ClientEntity clientBody = clientOptional.get();

            clientBody.setFirstName(clientEntity.getFirstName());
            clientBody.setLastName(clientEntity.getLastName());
            clientBody.setDateOfBirth(clientEntity.getDateOfBirth());
            clientBody.setPassword(clientEntity.getPassword());
            clientBody.setTelephone(clientEntity.getTelephone());
            clientBody.setCellphone(clientEntity.getCellphone());
            clientBody.setEmail(clientEntity.getEmail());
            clientBody.setOccupation(clientEntity.getOccupation());

            clientRepository.save(clientEntity);

            return ResponseEntity.status(HttpStatus.OK).body("Client updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
    }

    /**
     * Deletes an existing client
     */
    @Override
    public ResponseEntity<Object> deleteClient(Long fiscalNumber) {

        Optional<ClientEntity> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

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
    @Override
    public List<Account> findAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        Iterable<AccountEntity> accountList = accountRepository.findAll();

        accountList.forEach(account -> {
            accounts.add(bankingBuilder.convertToAccountModel(account));
        });

        return accounts;
    }

    /**
     * Finds account by account number
     */
    @Override
    public ResponseEntity<Object> findByAccountNumber(Long accountNumber) {

        Optional<AccountEntity> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        return accountOptional.<ResponseEntity<Object>>map(account ->
                        ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToAccountModel(account)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account does not exist."));
    }

    /**
     * Creates a new account
     */
    @Override
    public ResponseEntity<Object> addAccount(Account account, Long fiscalNumber, Date dateOfBirth) {

        Optional<ClientEntity> clientFiscalNumber = clientRepository.findByFiscalNumber(fiscalNumber);
        Optional<ClientEntity> clientAge = clientRepository.findByDateOfBirth(dateOfBirth);

        if (clientFiscalNumber.isPresent() && clientAge.isPresent()) {
            account.setBalance(50.0);
            accountRepository.save(bankingBuilder.convertToAccountEntity(account));
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist");
        }
    }

    /**
     * Deletes an existing account
     */
    @Override
    public ResponseEntity<Object> deleteAccount(Long accountNumber) {
        Optional<AccountEntity> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            AccountEntity existingAccountEntity = accountOptional.get();
            accountRepository.delete(existingAccountEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist.");
        }

    }

    /**
     * Returns a list of all existing cards
     */
    @Override
    public List<Card> findAllCards() {
        List<Card> cards = new ArrayList<>();
        Iterable<CardEntity> cardList = cardRepository.findAll();

        cardList.forEach(card -> {
            cards.add(bankingBuilder.convertToCardModel(card));
        });

        return cards;
    }

    /**
     * Finds card by card number
     */
    @Override
    public ResponseEntity<Object> findByCardNumber(Long cardNumber) {

        Optional<CardEntity> cardOptional = cardRepository.findByCardNumber(cardNumber);

        return cardOptional.<ResponseEntity<Object>>map(card ->
                        ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.convertToCardModel(card)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card does not exist."));
    }

    /**
     * Creates a new card
     */
    @Override
    public ResponseEntity<Object> addCard(Card card, Long accountNumber) {

        Optional<AccountEntity> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            cardRepository.save(bankingBuilder.convertToCardEntity(card));
            return ResponseEntity.status(HttpStatus.CREATED).body("Card created successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist");
        }
    }

    /**
     * Updates card details
     */
    @Override
    public ResponseEntity<Object> updateCardPin(Card card, Long cardNumber) {

        Optional<CardEntity> cardOptional = cardRepository.findByCardNumber(cardNumber);
        CardEntity cardEntity = bankingBuilder.convertToCardEntity(card);

        if (cardOptional.isPresent()) {
            CardEntity cardBody = cardOptional.get();

            cardBody.setCardPin(card.getCardPin());

            cardRepository.save(cardEntity);

            return ResponseEntity.status(HttpStatus.OK).body("Card pin updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found.");
        }
    }

    /**
     * Deletes an existing card
     */
    @Override
    public ResponseEntity<Object> deleteCard(Long cardNumber) {

        Optional<CardEntity> cardOptional = cardRepository.findByCardNumber(cardNumber);

        if (cardOptional.isPresent()) {
            CardEntity existingCardEntity = cardOptional.get();
            cardRepository.delete(existingCardEntity);
            return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully.");

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card does not exist.");
        }
    }

    /**
     * Shows the transaction history for a specific account
     */
    @Override
    public List<Transaction> findTransanctionsByAccountNumber(Long accountNumber) {

        List<Transaction> transactions = new ArrayList<>();
        Optional<AccountEntity> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            Optional<List<TransactionEntity>> transactionsEntity = transactionRepository.findTransactionByAccountNumber(accountNumber);

            transactionsEntity.ifPresent(transactionEntities -> transactionEntities.forEach(transaction -> {
                transactions.add(bankingBuilder.convertToTransactionModel(transaction));
            }));
        }

        return transactions;
    }
}