package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Client;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    Client login(Long fiscalNumber, String password);

    ResponseEntity<Object> atmLogin(Long cardNumber, Integer cardPin);
}