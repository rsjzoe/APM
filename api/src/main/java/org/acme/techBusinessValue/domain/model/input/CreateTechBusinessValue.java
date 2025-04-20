package org.acme.techBusinessValue.domain.model.input;

import org.acme.lib.NumberHumanizer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateTechBusinessValue {
    private double businessValue;
    private double technicalDebt;
    private Long appId;

    public CreateTechBusinessValue(double businessValue, double technicalDebt, Long appId) {
        this.businessValue = NumberHumanizer.oneDecimal(businessValue);
        this.technicalDebt = NumberHumanizer.oneDecimal(technicalDebt);
        this.appId = appId;
    }

    public boolean checkIfValid() {
        if (businessValue < 0 || businessValue > 5)
            return false;
        if (technicalDebt < 0 || technicalDebt > 5)
            return false;
        return true;
    }
}
