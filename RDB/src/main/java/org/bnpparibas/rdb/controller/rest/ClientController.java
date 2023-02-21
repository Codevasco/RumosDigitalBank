package org.bnpparibas.rdb.controller.rest;

import org.bnpparibas.rdb.domain.ClientDomain;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.service.BankingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private BankingServiceImpl bankingService;

    @GetMapping(path = "/clients")
    public List<ClientDomain> getAllClients() {
        return bankingService.findAllClients();
    }

    @PostMapping(path = "/add/{nif}")
    public ResponseEntity<Object> addClient(@RequestBody ClientDomain clientDomain, Long nif) {
        return bankingService.addClient(clientDomain, nif);
    }

    @GetMapping(path = "/{nif}")
    public ResponseEntity<Object> getClient(@PathVariable Long nif) {
        return bankingService.findByNif(nif);
    }

    @GetMapping(path = "/update/{nif}")
    public ResponseEntity<Object> updateClient(@RequestBody Client client, Long nif) {
        return bankingService.updateClient(client, nif);
    }

    @DeleteMapping(path = "/delete/{nif}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long nif) {
        return bankingService.deleteClient(nif);
    }

}