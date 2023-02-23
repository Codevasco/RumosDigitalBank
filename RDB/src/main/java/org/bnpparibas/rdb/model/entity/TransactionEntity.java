package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID", nullable = false)
    private Integer transactionId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private Long accountNumber;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private String transactionAmount;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_TRANSACTION", nullable = false)
    private Date transactionDate;
}