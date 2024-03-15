package seedu.findvisor.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles DateTime parsing and formatting.
 */
public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm");

    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Converts a String into a LocalDateTime object. The expected format is yyyy-MM-dd'T'HH:mm. For
     * example, 2023-01-29T14:00.
     *
     * @param input The string to be converted to a LocalDateTime object.
     * @return The resulting LocalDateTime object after the conversion.
     * @throws DateTimeParseException If the String is not in the expected format.
     */
    public static LocalDateTime parseDateTimeString(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DATE_TIME_INPUT_FORMAT);
    }

    /**
     * Converts a LocalDateTime object into a String in input format. The format is yyyy-MM-dd'T'HH:mm. For
     * example, 2023-01-29T14:00.
     *
     * @param dateTime The LocalDateTime object to be converted.
     * @return The resulting string after the conversion.
     */
    public static String dateTimeToInputString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_INPUT_FORMAT);
    }

    /**
     * Converts a LocalDateTime object into a String in output format. The format is yyyy-MM-dd HH:mm. For
     * example, 2023-01-29 14:00.
     *
     * @param dateTime The LocalDateTime object to be converted.
     * @return The resulting string after the conversion.
     */
    public static String dateTimeToString(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_OUTPUT_FORMAT);
    }

    /**
     * Checks if the given LocalDateTime object is after the current date and time.
     *
     * @param dateTime The LocalDateTime object to be checked.
     * @return True if the given LocalDateTime object is after the current date and time, false otherwise.
     */
    public static boolean isAfterCurrentDateTime(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }

}
