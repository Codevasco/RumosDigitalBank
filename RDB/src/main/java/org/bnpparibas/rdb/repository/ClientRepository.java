package org.bnpparibas.rdb.repository;

import org.bnpparibas.rdb.model.persistence.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByNif(Long nif);
}