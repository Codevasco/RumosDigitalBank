package org.bnpparibas.rdb.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AccountDomain {

    private UUID accountId;

    private Long accountNumber;

    private ClientDomain primaryHolder;

    private ClientDomain secondaryHolder;

    private Double balance;

    private ArrayList<TransactionDomain> transactionHistory;

    private Date accountCreationDate;

    private Date accountUpdateDate;
}