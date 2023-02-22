package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private UUID accountId;

    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;

    @ManyToOne
    @JoinColumn(name = "PRIMARY_HOLDER_FK")
    private ClientEntity primaryHolder;

    @ManyToOne
    @JoinColumn(name = "SECONDARY_HOLDER_FK")
    private ClientEntity secondaryHolder;

    @Column(name = "BALANCE")
    private Double balance;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATED_ACCOUNT")
    private Date accountCreationDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_UPDATED_ACCOUNT")
    private Date accountUpdateDate;
}