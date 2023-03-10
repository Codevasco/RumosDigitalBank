package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByAccountNumber(Integer accountNumber);

    Iterable<Account> findByPrimaryHolder(Long primaryHolder);

    Iterable<Account> findBySecondaryHolder(Long secondaryHolder);

    Optional<Account> findByPrimaryHolderAndSecondaryHolder(Long primaryHolder, Long secondaryHolder);
}