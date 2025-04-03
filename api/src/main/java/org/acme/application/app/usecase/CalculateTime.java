package org.acme.application.app.usecase;

import org.acme.application.domain.model.Time;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculateTime {
    public Time calcul(double businessValue, double technicalDebt) throws InvalidTechBusinessValueException {
        if (businessValue < 0 || businessValue > 5 || technicalDebt < 0 || technicalDebt > 5) {
            throw new InvalidTechBusinessValueException();
        }
        if (businessValue <= 2.5 && technicalDebt <= 2.5) {
            return Time.eliminate;
        }

        if (businessValue <= 2.5 && technicalDebt > 2.5) {
            return Time.tolerate;
        }

        if (businessValue > 2.5 && technicalDebt > 2.5) {
            return Time.invest;
        }

        if (businessValue > 2.5 && technicalDebt <= 2.5) {
            return Time.migrate;
        }

        return Time.tolerate;
    }
}
