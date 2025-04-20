package org.acme.techBusinessValue.domain.model.input;

import org.acme.lib.NumberHumanizer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTechBusinessValueWithoutApp {
    private double businessValue;
    private double technicalDebt;

    public CreateTechBusinessValueWithoutApp(double businessValue, double technicalDebt) {
        this.businessValue = NumberHumanizer.oneDecimal(businessValue);
        this.technicalDebt = NumberHumanizer.oneDecimal(technicalDebt);
    }

    public boolean checkIfValid() {
        if (businessValue < 0 || businessValue > 5)
            return false;
        if (technicalDebt < 0 || technicalDebt > 5)
            return false;
        return true;
    }
}
