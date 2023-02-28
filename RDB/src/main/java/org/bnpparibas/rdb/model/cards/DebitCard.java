package org.bnpparibas.rdb.model.cards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DebitCard {

    private Double plafond;
    private Integer withdrawalsPerDay;

}