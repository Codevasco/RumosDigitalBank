package org.bnpparibas.rdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {

    private Integer accountId;

    private Long accountNumber;

    private Client primaryHolder;

    private Client secondaryHolder;

    private Double balance;
}