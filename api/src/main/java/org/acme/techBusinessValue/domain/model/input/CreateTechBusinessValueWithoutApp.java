package org.acme.techBusinessValue.domain.model.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTechBusinessValueWithoutApp {
    private double businessValue;
    private double technicalDebt;

    public boolean checkIfValid() {
        if (businessValue < 0 || businessValue > 5)
            return false;
        if (technicalDebt < 0 || technicalDebt > 5)
            return false;
        return true;
    }
}
