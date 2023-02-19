package controller.tags;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Dates {
    private Dates() {}

    public static String formatLocalDateTime(java.sql.Timestamp timestamp, String pattern) {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
