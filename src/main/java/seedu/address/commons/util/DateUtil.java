package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for parsing and formatting dates
 */
public class DateUtil {
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_DISPLAY_FORMAT = "dd MMM yyyy HH:mm";

    /**
     * Formats a LocalDateTime object into a string for display
     * @return Parsed date time frmo String, or if unable to parse, returns null
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT));
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

}
