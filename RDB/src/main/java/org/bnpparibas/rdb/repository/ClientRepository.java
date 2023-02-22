package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {
    Optional<ClientEntity> findByFiscalNumber(Long fiscalNumber);
}