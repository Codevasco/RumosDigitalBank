package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.service.WebServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    private WebServiceImpl webService;

    @GetMapping("/login") // TODO FIX
    public ResponseEntity<Object> getLogin(Model model, @RequestParam Long fiscalNumber, @RequestParam String password) {
        ResponseEntity<Object> clientAuthenticated = webService.login(fiscalNumber, password);
        model.addAttribute("clientAuthenticated", clientAuthenticated);
        // return "wip";
        return null;
    }

    @GetMapping("/ATM")
    public ResponseEntity<Object> getAtmLogin(@RequestParam Long cardNumber, @RequestParam Integer cardPin) {
        return webService.atmLogin(cardNumber, cardPin);
    }
}