package org.acme.lib;

public class NumberHumanizer {
    public static double oneDecimal(double number) {
        return Math.round(number * 10.0) / 10.0;
    }
}
