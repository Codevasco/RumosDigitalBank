package org.bnpparibas.rdb.service.implementation;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.model.builder.BankingBuilder;
import org.bnpparibas.rdb.repository.AccountRepository;
import org.bnpparibas.rdb.repository.CardRepository;
import org.bnpparibas.rdb.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CardServiceImpl implements CardService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CardRepository cardRepository;


    @Autowired
    private BankingBuilder bankingBuilder;


    @Override
    public List<Card> findAllCards() {

        List<Card> cards = new ArrayList<>();
        Iterable<Card> cardList = cardRepository.findAll();

        cardList.forEach(card -> {
            cards.add(bankingBuilder.cardBuilder(card));
        });

        return cards;
    }

    /**
     * Finds card by card number
     */
    @Override
    public ResponseEntity<Object> findByCardNumber(Long cardNumber) {

        Optional<Card> cardOptional = cardRepository.findByCardNumber(cardNumber);

        return cardOptional.<ResponseEntity<Object>>map(card ->
                        ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.cardBuilder(card)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card does not exist."));
    }

    /**
     * Creates a new card
     */
    @Override
    public ResponseEntity<Object> addCard(Card card, Long accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            cardRepository.save(bankingBuilder.cardBuilder(card));

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

        Optional<Card> cardOptional = cardRepository.findByCardNumber(cardNumber);

        if (cardOptional.isPresent()) {
            Card cardBody = cardOptional.get();
            cardBody.setCardPin(card.getCardPin());
            cardRepository.save(card);

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

        Optional<Card> cardOptional = cardRepository.findByCardNumber(cardNumber);

        if (cardOptional.isPresent()) {
            Card existingCard = cardOptional.get();
            cardRepository.delete(existingCard);

            return ResponseEntity.status(HttpStatus.OK).body("Card deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card does not exist.");
        }
    }
}