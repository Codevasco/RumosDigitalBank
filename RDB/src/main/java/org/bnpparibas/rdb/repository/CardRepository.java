package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, String>  {
}