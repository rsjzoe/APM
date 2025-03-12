package org.acme.cost.domain.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCostWithoutApp {
    private double costBuild;
    private double costRun;
}
