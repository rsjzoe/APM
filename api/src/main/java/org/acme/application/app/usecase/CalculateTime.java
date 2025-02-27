package org.acme.application.app.usecase;

import org.acme.application.domain.model.Time;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CalculateTime {
    public Time calcul(double businessValue, double cost) {
        // Définir les seuils
        double businessValueThresholdLow = 50;
        double businessValueThresholdHigh = 100;
        double costThresholdLow = 50;
        double costThresholdHigh = 100;

        // Cas 1 : Invest, business value eleve et cout faible
        if (businessValue > businessValueThresholdHigh && cost <= costThresholdHigh) {
            return Time.invest;
        }

        // Cas 2 : Migrate, business value eleve et cout eleve
        if (businessValue > businessValueThresholdHigh && cost > costThresholdHigh) {
            return Time.migrate;
        }

        // Cas 3 : Tolerate, business value faible et cout faible
        if (businessValue <= businessValueThresholdLow && cost <= costThresholdLow) {
            return Time.tolerate;
        }

        // Cas 4 : Eliminate, business value faible et cout eleve
        if (businessValue <= businessValueThresholdLow && cost > costThresholdLow) {
            return Time.eliminate;
        }

        // Par défaut, retourner une valeur (ex : Tolerate)
        return Time.tolerate;
    }
}
