package org.bnpparibas.rdb.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "CARD")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CARD_ID", nullable = false, unique = true)
    private Integer cardId;

    @Column(name = "CARD_NUMBER", nullable = false, updatable = false, unique = true)
    private Long cardNumber;

    @Column(name = "CARD_PIN", nullable = false, updatable = false)
    private Integer cardPin;

    @OneToOne
    @JoinColumn(name = "CARD_OWNER_FK", nullable = false)
    private Client cardOwner;

    @OneToOne
    @JoinColumn(name = "CARD_ACCOUNT_FK", nullable = false)
    private Account cardAccount;

    @Column(name = "WITHDRAWALS_PER_DAY")
    private Integer withdrawalsPerDay;

    @Column(name ="WITHDRAWN_AMOUNT_PER_DAY")
    private Double withdrawnAmountPerDay;
}