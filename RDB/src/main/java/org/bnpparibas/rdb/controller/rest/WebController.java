package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.service.WebServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    private WebServiceImpl webService;

    @GetMapping("/login")
    public ResponseEntity<Object> getLogin(@RequestBody Long fiscalNumber, String password) {
        return webService.login(fiscalNumber, password);
    }

    @GetMapping("/ATM")
    public ResponseEntity<Object> getAtmLogin(@RequestBody Long cardNumber, Integer cardPin) {
        return webService.atmLogin(cardNumber, cardPin);
    }
}