package org.bnpparibas.rdb.model.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transfer {

    private Long fromAccountNumber;

    private Long toAccountNumber;

    private Double transferAmount;
}