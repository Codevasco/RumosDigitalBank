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
    private Integer cardNumber;

    @Column(name = "CARD_PIN", nullable = false, updatable = false)
    private Integer cardPin;

    @Column(name = "FISCAL_NUMBER", nullable = false)
    private Long fiscalNumber;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private Integer accountNumber;
}