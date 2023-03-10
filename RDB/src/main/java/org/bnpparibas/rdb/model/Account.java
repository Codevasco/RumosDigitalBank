package org.bnpparibas.rdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ACCOUNT_ID", nullable = false, unique = true)
    private Integer accountId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, updatable = false, unique = true)
    private Long accountNumber;

    @Column(name = "PRIMARY_HOLDER", nullable = false)
    private Long primaryHolder;

    @Column(name = "SECONDARY_HOLDER")
    private Long secondaryHolder;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;
}