package org.bnpparibas.rdb.model.builder;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;

import org.springframework.stereotype.Component;

@Component
public class BankingBuilder {

    public Client clientBuilder(Client client) {

        return Client.builder()
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

    public Account accountBuilder(Account account) {

        return Account.builder()
                .accountNumber(account.getAccountNumber())
                .primaryHolder(account.getPrimaryHolder())
                .secondaryHolder(account.getSecondaryHolder())
                .balance(account.getBalance())
                .build();
    }
}