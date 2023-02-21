package org.bnpparibas.rdb.service.builder;

import org.bnpparibas.rdb.domain.AccountDomain;
import org.bnpparibas.rdb.domain.ClientDomain;
import org.bnpparibas.rdb.model.Account;
import org.bnpparibas.rdb.model.Client;

import org.springframework.stereotype.Component;

@Component
public class BankingBuilder {

    public ClientDomain convertToClientDomain(Client client) {

        return ClientDomain.builder()
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

    public Client convertToClientEntity(ClientDomain clientDomain) {

        return Client.builder()
                .nif(clientDomain.getNif())
                .firstName(clientDomain.getFirstName())
                .lastName(clientDomain.getLastName())
                .password(clientDomain.getPassword())
                .telephone(clientDomain.getTelephone())
                .cellphone(clientDomain.getCellphone())
                .email(clientDomain.getEmail())
                .occupation(clientDomain.getOccupation())
                .build();
    }

    public AccountDomain convertToAccountDomain(Account account) {

        return AccountDomain.builder()
                .accountNumber(account.getAccountNumber())
                .primaryHolder(convertToClientDomain(account.getPrimaryHolder()))
                .secondaryHolder(convertToClientDomain(account.getSecondaryHolder()))
                .balance(account.getBalance())
                .build();
    }

    public Account convertToAccountEntity(AccountDomain accountDomain) {

        return Account.builder()
                .accountNumber(accountDomain.getAccountNumber())
                .primaryHolder(convertToClientEntity(accountDomain.getPrimaryHolder()))
                .secondaryHolder(convertToClientEntity(accountDomain.getSecondaryHolder()))
                .balance(accountDomain.getBalance())
                .build();
    }
}