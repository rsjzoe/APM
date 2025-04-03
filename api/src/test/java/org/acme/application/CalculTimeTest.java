package org.acme.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.acme.application.app.usecase.CalculateTime;
import org.acme.application.domain.model.Time;
import org.acme.techBusinessValue.domain.exception.InvalidTechBusinessValueException;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class CalculTimeTest {
    @Inject
    CalculateTime calculateTime;

    @Test
    void tolerateTest() throws InvalidTechBusinessValueException {
        assertEquals(Time.tolerate, calculateTime.calcul(2, 2.6));
    }

    @Test
    void investTest() throws InvalidTechBusinessValueException {
        assertEquals(Time.invest, calculateTime.calcul(3.4, 4));
    }

    @Test
    void migrateTest() throws InvalidTechBusinessValueException {
        assertEquals(Time.migrate, calculateTime.calcul(4.1, 1.5));
    }

    @Test
    void eliminateTest() throws InvalidTechBusinessValueException {
        assertEquals(Time.eliminate, calculateTime.calcul(1.5, 1.6));
    }

    @Test
    void invalidTimeTest() {
        assertThrows(InvalidTechBusinessValueException.class, () -> {
            calculateTime.calcul(6, 3);
        });
        assertThrows(InvalidTechBusinessValueException.class, () -> {
            calculateTime.calcul(3, 6);
        });
        assertThrows(InvalidTechBusinessValueException.class, () -> {
            calculateTime.calcul(6, 6);
        });
        assertThrows(InvalidTechBusinessValueException.class, () -> {
            calculateTime.calcul(-1, 3);
        });
        assertThrows(InvalidTechBusinessValueException.class, () -> {
            calculateTime.calcul(2, -3);
        });
        assertThrows(InvalidTechBusinessValueException.class, () -> {
            calculateTime.calcul(-2, -3);
        });
    }
}
