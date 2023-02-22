package org.bnpparibas.rdb.model.entity.cards;

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
    @Column(name = "CARD_ID")
    private UUID cardId;

    @Column(name = "CARD_PIN")
    private Integer cardPin;

    @OneToOne
    @JoinColumn(name = "CARD_OWNER_FK")
    private ClientEntity cardOwner;

    @OneToOne
    @JoinColumn(name = "CARD_ACCOUNT_FK")
    private AccountEntity cardAccount;
}