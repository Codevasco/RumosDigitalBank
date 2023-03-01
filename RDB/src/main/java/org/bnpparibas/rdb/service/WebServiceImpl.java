package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.entity.CardEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.repository.CardRepository;
import org.bnpparibas.rdb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class WebServiceImpl implements WebService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public ResponseEntity<Object> login(Long fiscalNumber, String password) {

        Optional<ClientEntity> client = clientRepository.findByFiscalNumberAndPassword(fiscalNumber, password);

        if (client.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found or password incorrect.");
        }
    }

    @Override
    public ResponseEntity<Object> atmLogin(Long cardNumber, Integer cardPin) {

        Optional<CardEntity> cardEntityNumber = cardRepository.findByCardNumber(cardNumber);
        Optional<CardEntity> cardEntityPin = cardRepository.findByCardPin(cardPin);

        if (cardEntityNumber.isPresent() && cardEntityPin.isPresent()
        ) {
            return ResponseEntity.status(HttpStatus.OK).body("(ATM) Logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Card not found or pin incorrect.");
        }
    }
}