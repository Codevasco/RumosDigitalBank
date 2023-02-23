package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.Card;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {

    List<Client> findAllClients();

    ResponseEntity<Object> findByFiscalNumber(Long fiscalNumber);

    ResponseEntity<Object> addClient(Client client, Long fiscalNumber);

    ResponseEntity<Object> updateClient(Client client, Long fiscalNumber);

    ResponseEntity<Object> deleteClient(Long fiscalNumber);

    List<Account> findAllAccounts();

    ResponseEntity<Object> findByAccountNumber(Long accountNumber);

    ResponseEntity<Object> addAccount(Account account, Long fiscalNumber);

    ResponseEntity<Object> deleteAccount(Long accountNumber);

    List<Card> findAllCards();

    ResponseEntity<Object> findByCardNumber(Long cardNumber);

    ResponseEntity<Object> addCard(Card card, Long accountNumber);

    ResponseEntity<Object> updateCardPin(Card card, Long cardNumber);

    ResponseEntity<Object> deleteCard(Long cardNumber);

    List<Transaction> findTransanctionsByAccountNumber(Long accountNumber);
}