package org.bnpparibas.rdb.service.builder;

import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;

import org.springframework.stereotype.Component;

@Component // TODO FIX
public class BankingBuilder {

    public ClientEntity convertToClientPojo(Client client) {

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

    public AccountEntity convertToAccountPojo(Account account) {

        return AccountEntity.builder()
                .accountNumber(account.getAccountNumber())
                .primaryHolder(convertToClientPojo(account.getPrimaryHolder()))
                .secondaryHolder(convertToClientPojo(account.getSecondaryHolder()))
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
}