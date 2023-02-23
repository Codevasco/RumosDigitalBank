package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.model.Card;
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

    @GetMapping("/cardDirectory") // TODO \ POSTMAN USE-CASE
    public List<Card> getFindAllCards() {
        return bankingService.findAllCards();
    }

    @GetMapping("/findCard")
    public ResponseEntity<Object> getFindByCardNumber(@PathVariable Long cardNumber) {
        return bankingService.findByCardNumber(cardNumber);
    }

    @GetMapping("/updateCardPin")
    public ResponseEntity<Object> getUpdateCardPin(@RequestBody Card card, Long cardNumber) {
        return bankingService.updateCardPin(card, cardNumber);
    }

    @PostMapping("/addCard")
    public ResponseEntity<Object> getAddCard(@RequestBody Card card, Long cardNumber) {
        return bankingService.addCard(card, cardNumber);
    }

    @DeleteMapping("/deleteCard")
    public ResponseEntity<Object> getDeleteCard(@PathVariable Long cardNumber) {
        return bankingService.deleteCard(cardNumber);
    }
}