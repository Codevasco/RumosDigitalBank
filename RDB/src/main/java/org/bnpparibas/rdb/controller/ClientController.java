package org.bnpparibas.rdb.controller;

import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.service.implementation.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping("/clientDirectory")
    public List<Client> getAllClients() {
        return clientService.findAllClients();
    }

    @GetMapping("/findClient")
    public ResponseEntity<Object> getClient(@RequestParam Long fiscalNumber) {
        return clientService.findByFiscalNumber(fiscalNumber);
    }

    @GetMapping("/updateClient")
    public ResponseEntity<Object> getUpdateClient(@RequestBody Client client, Long fiscalNumber) {
        return clientService.updateClient(client, fiscalNumber);
    }

    @DeleteMapping("/deleteClient")
    public ResponseEntity<Object> deleteClient(@RequestParam Long fiscalNumber) {
        return clientService.deleteClient(fiscalNumber);
    }
}