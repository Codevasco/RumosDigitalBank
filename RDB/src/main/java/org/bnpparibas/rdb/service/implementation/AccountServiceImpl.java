package org.bnpparibas.rdb.service.implementation;

import jakarta.transaction.Transactional;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.builder.BankingBuilder;
import org.bnpparibas.rdb.repository.AccountRepository;
import org.bnpparibas.rdb.repository.ClientRepository;
import org.bnpparibas.rdb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BankingBuilder bankingBuilder;


    @Override
    public List<Account> findAllAccounts(Long primaryHolder) {

        List<Account> accounts = new ArrayList<>();
        Iterable<Account> accountList = accountRepository.findByPrimaryHolder(primaryHolder);

        accountList.forEach(account -> {
            accounts.add(bankingBuilder.accountBuilder(account));
        });

        return accounts;
    }

    /**
     * Finds account by account number
     */
    @Override
    public ResponseEntity<Object> findByAccountNumber(Integer accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        return accountOptional.<ResponseEntity<Object>>map(account ->
                        ResponseEntity.status(HttpStatus.FOUND).body(bankingBuilder.accountBuilder(account)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account does not exist."));
    }

    /**
     * Creates a new account
     */
    @Override
    public ResponseEntity<Object> addAccount(Account account, Long primaryHolder, Long secondaryHolder) {

        Optional<Account> accountOptional = accountRepository.findByPrimaryHolderAndSecondaryHolder(primaryHolder, secondaryHolder);

        if (accountOptional.isPresent()) {

            account.setBalance(50.0);
            accountRepository.save(bankingBuilder.accountBuilder(account));

            return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client does not exist");
        }
    }

    @Override
    public ResponseEntity<Object> updateAccount(Account account, Integer accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            Account accountBody = accountOptional.get();

            accountBody.setSecondaryHolder(account.getSecondaryHolder());
            accountRepository.save(accountBody);

            return ResponseEntity.status(HttpStatus.OK).body("Client updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }
    }

    /**
     * Deletes an existing account
     */
    @Override
    public ResponseEntity<Object> deleteAccount(Integer accountNumber) {

        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);

        if (accountOptional.isPresent()) {
            Account existingAccount = accountOptional.get();
            accountRepository.delete(existingAccount);

            return ResponseEntity.status(HttpStatus.OK).body("Account deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist.");
        }

    }
}