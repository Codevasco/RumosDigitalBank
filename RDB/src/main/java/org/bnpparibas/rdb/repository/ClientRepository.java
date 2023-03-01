package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByFiscalNumber(Long fiscalNumber);

    Optional<Client> findByFiscalNumberAndPassword(Long fiscalNumber, String password);
}