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

    private Long accountNumber;

    @ManyToOne
    private ClientEntity primaryHolder;

    @ManyToOne
    private ClientEntity secondaryHolder;

    private Double balance;

    @Temporal(TemporalType.DATE)
    private Date accountCreationDate;

    @Temporal(TemporalType.DATE)
    private Date accountUpdateDate;
}