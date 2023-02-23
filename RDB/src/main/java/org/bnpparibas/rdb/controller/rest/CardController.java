package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.model.cards.Card;
import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cards")
public class CardController { // TODO POSTMAN

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping("/card-directory")
    public List<Card> getAllCards() {
        return bankingService.findAllCards();
    }

    @GetMapping("/{cardNumber}")
    public ResponseEntity<Object> getFindByCardNumber(@PathVariable Long cardNumber) {
        return bankingService.findByCardNumber(cardNumber);
    }

    @PostMapping("/add/{cardNumber")
    public ResponseEntity<Object> getAddCard(@RequestBody Card card, @PathVariable Long cardNumber) {
        return bankingService.addCard(card, cardNumber);
    }

    @DeleteMapping("/delete/{cardNumber}")
    public ResponseEntity<Object> getDeleteCard(@PathVariable Long cardNumber) {
        return bankingService.deleteCard(cardNumber);
    }
}