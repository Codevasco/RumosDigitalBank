package org.bnpparibas.rdb.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TRANSACTION_ID", nullable = false, unique = true)
    private Integer transactionId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    private Integer accountNumber;

    @Column(name = "TRANSACTION_TYPE", nullable = false)
    private String transactionType;

    @Column(name = "TRANSACTION_AMOUNT", nullable = false)
    private String transactionAmount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATE_TRANSACTION", nullable = false)
    private Date transactionDate;
}