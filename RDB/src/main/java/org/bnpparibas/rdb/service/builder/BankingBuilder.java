package org.bnpparibas.rdb.service.builder;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.cards.Card;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.CardEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;

import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class BankingBuilder {

    public ClientEntity convertToClientModel(Client client) {

        return ClientEntity.builder()
                .nif(client.getNif())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .password(client.getPassword())
                .telephone(client.getTelephone())
                .cellphone(client.getCellphone())
                .email(client.getEmail())
                .occupation(client.getOccupation())
                .build();
    }

    public Client convertToClientEntity(ClientEntity clientEntity) {

        return Client.builder()
                .nif(clientEntity.getNif())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .password(clientEntity.getPassword())
                .telephone(clientEntity.getTelephone())
                .cellphone(clientEntity.getCellphone())
                .email(clientEntity.getEmail())
                .occupation(clientEntity.getOccupation())
                .build();
    }

    public AccountEntity convertToAccountModel(Account account) {

        return AccountEntity.builder()
                .accountNumber(account.getAccountNumber())
                .primaryHolder(convertToClientModel(account.getPrimaryHolder()))
                .secondaryHolder(convertToClientModel(account.getSecondaryHolder()))
                .balance(account.getBalance())
                .build();
    }

    public Account convertToAccountEntity(AccountEntity accountEntity) {

        return Account.builder()
                .accountNumber(accountEntity.getAccountNumber())
                .primaryHolder(convertToClientEntity(accountEntity.getPrimaryHolder()))
                .secondaryHolder(convertToClientEntity(accountEntity.getSecondaryHolder()))
                .balance(accountEntity.getBalance())
                .build();
    }

    public CardEntity convertToCardModel(Card card) {

        return CardEntity.builder()
                .cardPin(card.getCardPin())
                .cardOwner(card.getCardOwner())
                .cardAccount(card.getCardAccount())
                .build();
    }

    public Card convertToCardEntity(CardEntity cardEntity) {

        return Card.builder()
                .cardPin(cardEntity.getCardPin())
                .cardOwner(cardEntity.getCardOwner())
                .cardAccount(cardEntity.getCardAccount())
                .build();
    }

    public TransactionEntity convertToTransactionModel(Transaction transaction) {

        return TransactionEntity.builder()
                .accountNumber(transaction.getAccountNumber())
                .transactionType(transaction.getTransactionType())
                .transactionAmount(transaction.getTransactionAmount())
                .build();
    }

    public Transaction convertToTransactionEntity (TransactionEntity transactionEntity) {

        return Transaction.builder()
                .accountNumber(transactionEntity.getAccountNumber())
                .transactionType(transactionEntity.getTransactionType())
                .transactionAmount(transactionEntity.getTransactionAmount())
                .build();

    }
}