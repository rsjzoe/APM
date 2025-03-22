package org.acme.cost.domain.model.output;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostOutputMonth {
    private String month;
    private int monthValue;
    private CostOutput data;
}
