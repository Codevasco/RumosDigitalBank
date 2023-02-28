package org.bnpparibas.rdb.controller.web;

import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.bnpparibas.rdb.service.WebServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebController {

    @Autowired
    private WebServiceImpl webService;

    @GetMapping("/index")
    public ResponseEntity<Object> getLogin(@RequestBody Long fiscalNumber, String password) {
        return webService.login(fiscalNumber, password);
    }

    @GetMapping("/ATM")
    public ResponseEntity<Object> getAtmLogin(@RequestBody Long cardNumber, Integer cardPin) {
        return webService.atmLogin(cardNumber, cardPin);
    }
}