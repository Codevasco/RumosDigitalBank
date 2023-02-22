package org.bnpparibas.rdb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.bnpparibas.rdb.model.entity.ClientEntity;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "CARD")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CARD_ID", nullable = false)
    private UUID cardId;

    @Column(name = "CARD_PIN", nullable = false, updatable = false)
    private Integer cardPin;

    @OneToOne
    @JoinColumn(name = "CARD_OWNER_FK", nullable = false)
    private ClientEntity cardOwner;

    @OneToOne
    @JoinColumn(name = "CARD_ACCOUNT_FK", nullable = false)
    private AccountEntity cardAccount;
}