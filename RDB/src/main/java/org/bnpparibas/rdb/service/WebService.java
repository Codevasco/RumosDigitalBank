package org.bnpparibas.rdb.service;

import org.springframework.http.ResponseEntity;

public interface WebService {

    ResponseEntity<Object> login(Long fiscalNumber, String password);

    ResponseEntity<Object> atmLogin(Long cardNumber, Integer cardPin);
}