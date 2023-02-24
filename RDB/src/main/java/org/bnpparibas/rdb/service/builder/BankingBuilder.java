package org.bnpparibas.rdb.service.builder;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.CardEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;

import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.springframework.stereotype.Component;

@Component
public class BankingBuilder {

    public Client convertToClientModel(ClientEntity clientEntity) {

        return Client.builder()
                .fiscalNumber(clientEntity.getFiscalNumber())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .password(clientEntity.getPassword())
                .telephone(clientEntity.getTelephone())
                .cellphone(clientEntity.getCellphone())
                .email(clientEntity.getEmail())
                .occupation(clientEntity.getOccupation())
                .build();
    }

    public ClientEntity convertToClientEntity(Client client) {

        return ClientEntity.builder()
                .fiscalNumber(client.getFiscalNumber())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .password(client.getPassword())
                .telephone(client.getTelephone())
                .cellphone(client.getCellphone())
                .email(client.getEmail())
                .occupation(client.getOccupation())
                .build();
    }

    public Account convertToAccountModel(AccountEntity accountEntity) {

        return Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .primaryHolder(convertToClientModel(accountEntity.getPrimaryHolder()))
                .secondaryHolder(convertToClientModel(accountEntity.getSecondaryHolder()))
                .balance(accountEntity.getBalance())
                .build();
    }

    public AccountEntity convertToAccountEntity(Account account) {

        return AccountEntity.builder()
                .accountNumber(account.getAccountNumber())
                .primaryHolder(convertToClientEntity(account.getPrimaryHolder()))
                .secondaryHolder(convertToClientEntity(account.getSecondaryHolder()))
                .balance(account.getBalance())
                .build();
    }

    public Card convertToCardModel(CardEntity cardEntity) {

        return Card.builder()
                .cardPin(cardEntity.getCardPin())
                .cardOwner(cardEntity.getCardOwner())
                .cardAccount(cardEntity.getCardAccount())
                .build();
    }

    public CardEntity convertToCardEntity(Card card) {

        return CardEntity.builder()
                .cardPin(card.getCardPin())
                .cardOwner(card.getCardOwner())
                .cardAccount(card.getCardAccount())
                .build();
    }

    public Transaction convertToTransactionModel(TransactionEntity transactionEntity) {

        return Transaction.builder()
                .accountNumber(transactionEntity.getAccountNumber())
                .transactionType(transactionEntity.getTransactionType())
                .transactionAmount(transactionEntity.getTransactionAmount())
                .build();
    }

    public TransactionEntity convertToTransactionEntity(Transaction transaction) {

        return TransactionEntity.builder()
                .accountNumber(transaction.getAccountNumber())
                .transactionType(transaction.getTransactionType())
                .transactionAmount(transaction.getTransactionAmount())
                .build();
    }

    public TransactionEntity newTransaction(Transfer transfer, Long accountNumber, String transactionType) {

        return TransactionEntity.builder()
                .accountNumber(accountNumber)
                .transactionType(transactionType)
                .transactionAmount(transfer.getTransferAmount().toString())
                .build();
    }

    public TransactionEntity newTransaction(Deposit deposit, Long accountNumber, String transactionType) {

        return TransactionEntity.builder()
                .accountNumber(accountNumber)
                .transactionType(transactionType)
                .transactionAmount(deposit.getDepositAmount().toString())
                .build();
    }

    public TransactionEntity newTransaction(Withdrawal withdrawal, Long accountNumber, String transactionType) {

        return TransactionEntity.builder()
                .accountNumber(accountNumber)
                .transactionType(transactionType)
                .transactionAmount(withdrawal.getWithdrawalAmount().toString())
                .build();
    }
}