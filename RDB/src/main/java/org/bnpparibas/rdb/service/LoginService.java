package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.model.Client;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    Client login(Long fiscalNumber, String password);

    Card atmLogin(Integer cardNumber, Integer cardPin);
}