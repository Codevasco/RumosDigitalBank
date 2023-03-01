package org.bnpparibas.rdb.service.implementation;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.builder.BankingBuilder;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.bnpparibas.rdb.repository.AccountRepository;
import org.bnpparibas.rdb.repository.ClientRepository;
import org.bnpparibas.rdb.repository.TransactionRepository;
import org.bnpparibas.rdb.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankingBuilder bankingBuilder;


    @Override
    public ResponseEntity<Object> deposit(Deposit deposit, Long fiscalNumber) {

        List<Account> account = new ArrayList<>();
        Account accountDeposit = null;

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isPresent()) {

            Optional<Account> accountNumber = accountRepository.findByAccountNumber(deposit.getAccountNumber());

            if (accountNumber.isPresent()) {
                accountDeposit = accountNumber.get();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
            }

            if (accountDeposit.getBalance() < deposit.getDepositAmount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough funds to complete transaction.");

            } else {
                synchronized (this) {
                    accountDeposit.setBalance(accountDeposit.getBalance() + deposit.getDepositAmount());
                    account.add(accountDeposit);
                    accountRepository.saveAll(account);

                    Transaction depositTransaction = bankingBuilder.newTransaction(deposit, accountDeposit.getAccountNumber(), "DEPOSIT");
                    transactionRepository.save(depositTransaction);
                }
                return ResponseEntity.status(HttpStatus.OK).body(deposit.getDepositAmount() + " deposited successfully.");
            }

        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Client not found.");
        }
    }

    @Override
    public ResponseEntity<Object> withdraw(Withdrawal withdrawal, Long fiscalNumber) {

        List<Account> account = new ArrayList<>();
        Account accountWithdrawal = null;

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isPresent()) {

            Optional<Account> accountNumber = accountRepository.findByAccountNumber(withdrawal.getAccountNumber());

            if (accountNumber.isPresent()) {
                accountWithdrawal = accountNumber.get();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
            }

            if (accountWithdrawal.getBalance() < withdrawal.getWithdrawalAmount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough funds to complete transaction.");

            } else {
                synchronized (this) {
                    accountWithdrawal.setBalance(accountWithdrawal.getBalance() - withdrawal.getWithdrawalAmount());
                    account.add(accountWithdrawal);
                    accountRepository.saveAll(account);

                    Transaction withdrawalTransaction = bankingBuilder.newTransaction(withdrawal, accountWithdrawal.getAccountNumber(), "WITHDRAWAL");
                    transactionRepository.save(withdrawalTransaction);
                }
                return ResponseEntity.status(HttpStatus.OK).body(withdrawal.getWithdrawalAmount() + " withdrawn successfully.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Client not found.");
        }
    }

    @Override
    public ResponseEntity<Object> transfer(Transfer transfer, Long fiscalNumber) {

        List<Account> account = new ArrayList<>();
        Account fromAccount = null;
        Account toAccount = null;

        Optional<Client> clientOptional = clientRepository.findByFiscalNumber(fiscalNumber);

        if (clientOptional.isPresent()) {

            // Get fromAccount information
            Optional<Account> fromAccountOptional = accountRepository.findByAccountNumber(transfer.getFromAccountNumber());

            if (fromAccountOptional.isPresent()) {
                fromAccount = fromAccountOptional.get();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(From) Account not found");
            }

            // Get toAccount information
            Optional<Account> toAccountOptional = accountRepository.findByAccountNumber(transfer.getToAccountNumber());

            if (toAccountOptional.isPresent()) {
                toAccount = toAccountOptional.get();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(To) Account not found");
            }

            if (fromAccount.getBalance() < transfer.getTransferAmount()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough funds to complete transaction.");
            } else {
                synchronized (this) {

                    fromAccount.setBalance(fromAccount.getBalance() - transfer.getTransferAmount());
                    account.add(fromAccount);

                    toAccount.setBalance(toAccount.getBalance() + transfer.getTransferAmount());
                    account.add(toAccount);

                    accountRepository.saveAll(account);

                    Transaction fromTransaction = bankingBuilder.newTransaction(transfer, fromAccount.getAccountNumber(), "TRANSFER - SENDER");
                    transactionRepository.save(fromTransaction);

                    Transaction toTransaction = bankingBuilder.newTransaction(transfer, toAccount.getAccountNumber(), "TRANSFER - RECEIVER");
                    transactionRepository.save(toTransaction);
                }
                return ResponseEntity.status(HttpStatus.OK).body(transfer.getTransferAmount() + " transferred successfully.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Client not found.");
        }
    }

    @Override
    public List<Transaction> findTransanctionsByAccountNumber(Long accountNumber) {

        List<Transaction> transactions = new ArrayList<>();
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            Optional<List<Transaction>> transactionList = transactionRepository.findTransactionByAccountNumber(accountNumber);
            transactionList.ifPresent(list -> list.forEach(transaction -> transactions.add(bankingBuilder.transactionBuilder(transaction))));
        }

        return transactions;
    }
}