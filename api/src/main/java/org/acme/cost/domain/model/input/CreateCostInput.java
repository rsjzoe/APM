package org.acme.cost.domain.model.input;

import org.acme.cost.domain.exception.InvalidCostException;

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

    public boolean checkIfValid() throws InvalidCostException {
        if (this.costBuild < 0 || this.costRun < 0) {
            return false;
        }
        return true;
    }
}
