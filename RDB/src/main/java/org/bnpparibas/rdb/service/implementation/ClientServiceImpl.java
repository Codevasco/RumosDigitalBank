package org.bnpparibas.rdb.service.implementation;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.builder.BankingBuilder;
import org.bnpparibas.rdb.repository.ClientRepository;
import org.bnpparibas.rdb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BankingBuilder bankingBuilder;

    @Override
    public List<Client> findAllClients() {

        List<Client> clients = new ArrayList<>();
        Iterable<Client> clientList = clientRepository.findAll();

        clientList.forEach(client -> {
            clients.add(bankingBuilder.clientBuilder(client));
        });

        return clients;
    }

    /**
     * Finds client by fiscal number (NIF)
     */
    @Override
    public ResponseEntity<Object> findByFiscalNumber(Long fiscalNumber) {

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        return clientOptional.<ResponseEntity<Object>>map(client ->
                        ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.clientBuilder(client)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found."));
    }

    /**
     * Creates a new client
     */
    @Override
    public ResponseEntity<Object> addClient(Client client, Long fiscalNumber) {

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isEmpty()) {
            Client clientEntity = bankingBuilder.clientBuilder(client);
            clientRepository.save(clientEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body("New client created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client already exists.");
        }
    }

    /**
     * Updates client details
     */
    @Override
    public ResponseEntity<Object> updateClient(Client client, Long fiscalNumber) {

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isPresent()) {
            Client clientBody = clientOptional.get();

            clientBody.setFirstName(client.getFirstName());
            clientBody.setLastName(client.getLastName());
            clientBody.setDateOfBirth(client.getDateOfBirth());
            clientBody.setPassword(client.getPassword());
            clientBody.setTelephone(client.getTelephone());
            clientBody.setCellphone(client.getCellphone());
            clientBody.setEmail(client.getEmail());
            clientBody.setOccupation(client.getOccupation());

            clientRepository.save(client);

            return ResponseEntity.status(HttpStatus.OK).body("Client updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
    }

    /**
     * Deletes an existing client
     */
    @Override
    public ResponseEntity<Object> deleteClient(Long fiscalNumber) {

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isPresent()) {
            Client existingClient = clientOptional.get();
            clientRepository.delete(existingClient);

            return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist.");
        }
    }
}