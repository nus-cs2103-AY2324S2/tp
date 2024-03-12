package seedu.address.commons.core.date;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Date {
    public static boolean isValidDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
