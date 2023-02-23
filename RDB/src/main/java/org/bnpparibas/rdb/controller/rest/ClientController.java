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

    @GetMapping("/client-directory")
    public List<Client> getAllClients() {
        return bankingService.findAllClients();
    }

    @GetMapping("/{fiscalNumber}")
    public ResponseEntity<Object> getClient(@PathVariable Long fiscalNumber) {
        return bankingService.findByFiscalNumber(fiscalNumber);
    }

    @PostMapping(path = "/add/{fiscalNumber}")
    public ResponseEntity<Object> getAddClient(@RequestBody Client client, Long nif) {
        return bankingService.addClient(client, nif);
    }

    @GetMapping(path = "/update/{fiscalNumber}")
    public ResponseEntity<Object> getUpdateClient(@RequestBody ClientEntity clientEntity, Long fiscalNumber) {
        return bankingService.updateClient(clientEntity, fiscalNumber);
    }

    @DeleteMapping(path = "/delete/{fiscalNumber}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long fiscalNumber) {
        return bankingService.deleteClient(fiscalNumber);
    }
}