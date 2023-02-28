package org.bnpparibas.rdb.controller.web;

import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebController {

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping("/index")
    public ResponseEntity<Object> getLogin(@RequestBody Long fiscalNumber, String password) {
        return bankingService.login(fiscalNumber, password);
    }
}