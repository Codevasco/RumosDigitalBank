package org.bnpparibas.rdb.service;

import org.bnpparibas.rdb.model.entity.AccountEntity;
import org.springframework.http.ResponseEntity;

public interface OperationsService {

    ResponseEntity<Object> deposit();

    ResponseEntity<Object> withdraw();

    ResponseEntity<Object> transfer();
}