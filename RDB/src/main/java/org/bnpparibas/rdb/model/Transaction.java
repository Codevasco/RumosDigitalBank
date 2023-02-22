package org.bnpparibas.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction {

    private UUID transactionId;

    private Long accountNumber;

    private Date transactionDate;

    private String transactionType;

    private String transactionAmount;
}