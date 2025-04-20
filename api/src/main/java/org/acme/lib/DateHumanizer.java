package org.acme.lib;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHumanizer {
    public static String format(LocalDateTime date) {
        return LocalDateTime.parse(date.toString(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).toString();
    }
}
