package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CardService {

    List<Card> findAllCards(Long client);

    ResponseEntity<Object> findByCardNumber(Integer cardNumber);

    ResponseEntity<Object> addCard(Card card, Long fiscalNumber, Integer accountNumber);

    ResponseEntity<Object> updateCardPin(Card card, Integer cardNumber);

    ResponseEntity<Object> deleteCard(Integer cardNumber);
}