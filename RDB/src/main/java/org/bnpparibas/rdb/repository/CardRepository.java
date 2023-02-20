package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.cards.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, String>  {
}