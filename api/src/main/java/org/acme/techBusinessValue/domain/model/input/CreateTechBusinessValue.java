package org.acme.techBusinessValue.domain.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechBusinessValue {
    private double businessValue;
    private double technicalDebt;
    private Long appId;

    public boolean checkIfValid() {
        if (businessValue < 0 || businessValue > 5)
            return false;
        if (technicalDebt < 0 || technicalDebt > 5)
            return false;
        return true;
    }
}
