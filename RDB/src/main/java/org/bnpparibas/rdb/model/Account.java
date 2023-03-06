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

    @ManyToOne
    @JoinColumn(name = "PRIMARY_HOLDER_FK", nullable = false)
    private Client primaryHolder;

    @ManyToOne
    @JoinColumn(name = "SECONDARY_HOLDER_FK", nullable = false)
    private Client secondaryHolder;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;
}