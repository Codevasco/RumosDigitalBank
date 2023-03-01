package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.service.implementation.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardServiceImpl cardService;

    @GetMapping("/cards")
    public List<Card> getFindAllCards() {
        return cardService.findAllCards();
    }

    @GetMapping("/findCard")
    public ResponseEntity<Object> getFindByCardNumber(@RequestParam Long cardNumber) {
        return cardService.findByCardNumber(cardNumber);
    }

    @GetMapping("/updateCardPin")
    public ResponseEntity<Object> getUpdateCardPin(@RequestBody Card card, Long cardNumber) {
        return cardService.updateCardPin(card, cardNumber);
    }

    @PostMapping("/addCard")
    public ResponseEntity<Object> getAddCard(@RequestBody Card card, Long cardNumber) {
        return cardService.addCard(card, cardNumber);
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity<Object> getDeleteCard(@RequestParam Long cardNumber) {
        return cardService.deleteCard(cardNumber);
    }
}