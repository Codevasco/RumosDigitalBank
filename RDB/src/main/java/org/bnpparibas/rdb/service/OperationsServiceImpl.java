package org.bnpparibas.rdb.service;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.bnpparibas.rdb.model.operations.Transfer;
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
    public ResponseEntity<Object> transfer(Transfer transfer, Long fiscalNumber) {

        List<AccountEntity> accountEntity = new ArrayList<>();
        AccountEntity fromAccount = null;
        AccountEntity toAccount = null;

        Optional<ClientEntity> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isPresent()) {

            // Get fromAccount information
            Optional<AccountEntity> fromAccountEntity = accountRepository.findByAccountNumber(transfer.getFromAccountNumber());

            if (fromAccountEntity.isPresent()) {
                fromAccount = fromAccountEntity.get();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(From) Account not found");
            }

            // Get toAccount information
            Optional<AccountEntity> toAccountEntity = accountRepository.findByAccountNumber(transfer.getToAccountNumber());

            if (toAccountEntity.isPresent()) {
                toAccount = toAccountEntity.get();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(To) Account not found");
            }

            if (fromAccount.getBalance() < transfer.getTransferAmount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough funds to complete transaction.");
            } else {

                synchronized (this) {

                    fromAccount.setBalance(fromAccount.getBalance() - transfer.getTransferAmount());
                    accountEntity.add(fromAccount);

                    toAccount.setBalance(toAccount.getBalance() + transfer.getTransferAmount());
                    accountEntity.add(toAccount);

                    accountRepository.saveAll(accountEntity);

                    TransactionEntity fromTransaction = bankingBuilder.newTransaction(transfer, fromAccount.getAccountNumber(), "TRANSFER - SENDER");
                    transactionRepository.save(fromTransaction);

                    TransactionEntity toTransaction = bankingBuilder.newTransaction(transfer, toAccount.getAccountNumber(), "TRANSFER - RECEIVER");
                    transactionRepository.save(toTransaction);
                }

                return ResponseEntity.status(HttpStatus.OK).body(transfer.getTransferAmount() + " transferred successfully.");
            }

        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Client not found.");
        }
    }
}