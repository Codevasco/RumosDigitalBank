package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String>  {

    Optional<Card> findByCardNumber(Integer cardNumber);

    Optional<Card> findByFiscalNumberAndAccountNumber(Long client, Integer account);

    Iterable<Card> findByFiscalNumber(Long client);

    Optional<Card> findByCardNumberAndCardPin(Integer cardNumber, Integer cardPin);
}