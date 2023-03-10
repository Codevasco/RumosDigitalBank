package org.bnpparibas.rdb.model.operations;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Withdrawal {

    private Integer accountNumber;

    private Double withdrawalAmount;
}