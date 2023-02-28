package org.bnpparibas.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Card {

    private Integer cardID;

    private Long cardNumber;

    private Integer cardPin;

    private ClientEntity cardOwner;

    private AccountEntity cardAccount;

    private Integer withdrawalsPerDay;

    private Double withdrawnAmountPerDay;
}