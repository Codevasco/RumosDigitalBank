package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController { // TODO POSTMAN

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping("/clientDirectory") // TODO \ POSTMAN USE-CASE
    public List<Client> getAllClients() {
        return bankingService.findAllClients();
    }

    @GetMapping("/findClient")
    public ResponseEntity<Object> getClient(@PathVariable Long fiscalNumber) {
        return bankingService.findByFiscalNumber(fiscalNumber);
    }

    @PostMapping(path = "/addClient")
    public ResponseEntity<Object> postAddClient(@RequestBody Client client, Long nif) {
        return bankingService.addClient(client, nif);
    }

    @GetMapping(path = "/updateClient")
    public ResponseEntity<Object> getUpdateClient(@RequestBody Client client, Long fiscalNumber) {
        return bankingService.updateClient(client, fiscalNumber);
    }

    @DeleteMapping(path = "/deleteClient")
    public ResponseEntity<Object> deleteClient(@PathVariable Long fiscalNumber) {
        return bankingService.deleteClient(fiscalNumber);
    }
}