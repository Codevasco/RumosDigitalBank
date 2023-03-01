package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

    List<Client> findAllClients();

    ResponseEntity<Object> findByFiscalNumber(Long fiscalNumber);

    ResponseEntity<Object> addClient(Client client, Long fiscalNumber);

    ResponseEntity<Object> updateClient(Client client, Long fiscalNumber);

    ResponseEntity<Object> deleteClient(Long fiscalNumber);
}