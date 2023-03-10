package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    Optional<List<Transaction>> findTransactionByAccountNumber(Integer accountNumber);
}