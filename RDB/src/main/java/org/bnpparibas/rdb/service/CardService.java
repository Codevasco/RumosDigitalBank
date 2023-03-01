package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CardService {
    List<Card> findAllCards();

    ResponseEntity<Object> findByCardNumber(Long cardNumber);

    ResponseEntity<Object> addCard(Card card, Long accountNumber);

    ResponseEntity<Object> updateCardPin(Card card, Long cardNumber);

    ResponseEntity<Object> deleteCard(Long cardNumber);
}