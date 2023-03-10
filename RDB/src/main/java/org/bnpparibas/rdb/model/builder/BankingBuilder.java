package org.bnpparibas.rdb.model.builder;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Card;
import org.bnpparibas.rdb.model.Client;

import org.bnpparibas.rdb.model.Transaction;
import org.bnpparibas.rdb.model.operations.Deposit;
import org.bnpparibas.rdb.model.operations.Transfer;
import org.bnpparibas.rdb.model.operations.Withdrawal;
import org.springframework.stereotype.Component;

@Component
public class BankingBuilder {

    public Client clientBuilder(Client client) {

        return Client.builder()
                .fiscalNumber(client.getFiscalNumber())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .dateOfBirth(client.getDateOfBirth())
                .password(client.getPassword())
                .telephone(client.getTelephone())
                .cellphone(client.getCellphone())
                .email(client.getEmail())
                .occupation(client.getOccupation())
                .build();
    }

    public Account accountBuilder(Account account) {

        return Account.builder()
                .accountNumber(account.getAccountNumber())
                .primaryHolder(account.getPrimaryHolder())
                .secondaryHolder(account.getSecondaryHolder())
                .balance(account.getBalance())
                .build();
    }

    public Card cardBuilder(Card card) {

        return Card.builder()
                .cardPin(card.getCardPin())
                .cardOwner(card.getCardOwner())
                .cardAccount(card.getCardAccount())
                .withdrawalsPerDay(card.getWithdrawalsPerDay())
                .withdrawnAmountPerDay(card.getWithdrawnAmountPerDay())
                .build();
    }

    public Transaction transactionBuilder(Transaction transaction) {

        return Transaction.builder()
                .accountNumber(transaction.getAccountNumber())
                .transactionType(transaction.getTransactionType())
                .transactionAmount(transaction.getTransactionAmount())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

    public Transaction newTransaction(Transfer transfer, Long accountNumber, String transactionType) {

        return Transaction.builder()
                .accountNumber(accountNumber)
                .transactionType(transactionType)
                .transactionAmount(transfer.getTransferAmount().toString())
                .build();
    }

    public Transaction newTransaction(Deposit deposit, Long accountNumber, String transactionType) {

        return Transaction.builder()
                .accountNumber(accountNumber)
                .transactionType(transactionType)
                .transactionAmount(deposit.getDepositAmount().toString())
                .build();
    }

    public Transaction newTransaction(Withdrawal withdrawal, Long accountNumber, String transactionType) {

        return Transaction.builder()
                .accountNumber(accountNumber)
                .transactionType(transactionType)
                .transactionAmount(withdrawal.getWithdrawalAmount().toString())
                .build();
    }
}