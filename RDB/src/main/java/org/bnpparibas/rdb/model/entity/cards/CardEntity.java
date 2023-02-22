package org.bnpparibas.rdb.model.entity.cards;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class CardEntity {

    @Id
    private UUID cardId;
}