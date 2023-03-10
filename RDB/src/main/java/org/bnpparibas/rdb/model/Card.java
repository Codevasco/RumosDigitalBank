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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "CARD_ID", nullable = false, unique = true)
    private Integer cardId;

    @Column(name = "CARD_NUMBER", nullable = false, updatable = false, unique = true)
    private Long cardNumber;

    @Column(name = "CARD_PIN", nullable = false, updatable = false)
    private Integer cardPin;

    @Column(name = "CLIENT", nullable = false)
    private Long client;

    @Column(name = "ACCOUNT", nullable = false)
    private Integer account;

    @Column(name ="WITHDRAWN_AMOUNT_PER_DAY")
    private Double withdrawnAmountPerDay;
}