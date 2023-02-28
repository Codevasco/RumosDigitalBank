package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String>  {

    Optional<CardEntity> findByCardNumber(Long cardNumber);

    Optional<CardEntity> findByCardPin(Integer cardPin);
}