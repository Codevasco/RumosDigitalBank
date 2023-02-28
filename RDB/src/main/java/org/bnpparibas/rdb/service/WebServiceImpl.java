package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class WebServiceImpl implements WebService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ResponseEntity<Object> login(Long fiscalNumber, String password) {

        Optional<ClientEntity> clientFiscalNumber = clientRepository.findByFiscalNumber(fiscalNumber);
        Optional<ClientEntity> clientPassword = clientRepository.findByPassword(password);

        if (clientFiscalNumber.isPresent() && clientPassword.isPresent()
        ) {
            return ResponseEntity.status(HttpStatus.OK).body("Logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found or password incorrect.");
        }
    }
}