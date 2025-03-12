package org.acme.cost.domain.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCostInput {
    private double costBuild;
    private double costRun;
    private Long applicationId;
}
