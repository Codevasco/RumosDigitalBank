package org.bnpparibas.rdb.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ACCOUNT_ID")
    private UUID accountId;

    private Long accountNumber;

    @ManyToOne
    private Client primaryHolder;

    @ManyToOne
    private Client secondaryHolder;

    private Double balance;

    @OneToMany(cascade = CascadeType.ALL)
    private ArrayList<Transaction> transactionHistory;

    @Temporal(TemporalType.DATE)
    private Date accountCreationDate;

    @Temporal(TemporalType.DATE)
    private Date accountUpdateDate;

}