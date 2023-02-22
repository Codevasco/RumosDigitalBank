package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TRANSACTION")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private UUID transactionId;

    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_AMOUNT")
    private String transactionAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_TRANSACTION")
    private Date transactionDate;
}