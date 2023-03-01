package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String>  {

    Optional<Card> findByCardNumber(Long cardNumber);

    Optional<Card> findByCardNumberAndCardPin(Long cardNumber, Integer cardPin);
}