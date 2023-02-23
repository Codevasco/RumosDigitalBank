package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.repository.AccountRepository;
import org.bnpparibas.rdb.repository.CardRepository;
import org.bnpparibas.rdb.repository.ClientRepository;
import org.bnpparibas.rdb.repository.TransactionRepository;
import org.bnpparibas.rdb.service.builder.BankingBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OperationsServiceImpl implements OperationsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankingBuilder bankingBuilder;


    @Override
    public ResponseEntity<Object> deposit() {
        return null;
    }

    @Override
    public ResponseEntity<Object> withdraw() {
        return null;
    }

    @Override
    public ResponseEntity<Object> transfer() {
        return null;
    }
}