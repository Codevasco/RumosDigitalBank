package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID", nullable = false)
    private Integer accountId;

    @Column(name = "ACCOUNT_NUMBER", nullable = false, updatable = false)
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name = "PRIMARY_HOLDER_FK", nullable = false)
    private ClientEntity primaryHolder;

    @ManyToOne
    @JoinColumn(name = "SECONDARY_HOLDER_FK", nullable = false)
    private ClientEntity secondaryHolder;

    @Column(name = "BALANCE", nullable = false)
    private Double balance;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED_ACCOUNT", nullable = false)
    private Date accountCreationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_UPDATED_ACCOUNT", nullable = false)
    private Date accountUpdateDate;
}