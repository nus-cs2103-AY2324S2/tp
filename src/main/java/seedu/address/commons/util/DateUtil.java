package seedu.address.commons.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Helper functions for parsing and formatting dates
 */
public class DateUtil {
    public static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_DISPLAY_FORMAT = "dd MMM yyyy, hh:mm a";

    /**
     * Parses a LocalDateTime object into a string for storage
     * @return Parsed date time from String, or if unable to parse, returns null
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        try {
            if (dateTime == null) {
                return null;
            }
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT));
        } catch (DateTimeException ex) {
            return null;
        }
    }

    /**
     * Formats a LocalDateTime object into a string for display
     * @return The formatted date time string if successful, or null if unable to format
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        try {
            if (dateTime == null) {
                return null;
            }
            return dateTime.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_FORMAT));
        } catch (DateTimeException ex) {
            return null;
        }
    }

}
