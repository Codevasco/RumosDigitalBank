package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    Optional<List<TransactionEntity>> findTransactionByAccountNumber(Long accountNumber);
}