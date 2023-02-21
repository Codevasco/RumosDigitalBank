package org.bnpparibas.rdb.model.persistence.cards;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Card {

    @Id
    private UUID cardId;
}