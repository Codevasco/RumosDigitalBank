package org.bnpparibas.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {

    private UUID accountId;

    private Long accountNumber;

    private Client primaryHolder;

    private Client secondaryHolder;

    private Double balance;

    private Date accountCreationDate;

    private Date accountUpdateDate;
}