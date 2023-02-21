package org.bnpparibas.rdb.model;

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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private UUID transactionId;

    private Long accountNumber;

    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    private String transactionType;

    private String transactionAmount;

}