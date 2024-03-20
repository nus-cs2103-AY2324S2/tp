package seedu.address.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling datetimes.
 */
public class DateTimeUtil {


    public static final String MESSAGE_CONSTRAINTS = "Date should be in the format of dd/MM/yyyy";
    /**
     * Returns a list of valid date time formats.
     */
    public static final String VALID_DATETIME_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Returns the valid formatter pattern.
     */
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(VALID_DATETIME_FORMAT);

    /**
     * Returns true if a given string is a valid date.
     *
     * @param test the date to be tested.
     * @return true if the date is valid.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDateTime.parse(test, FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the current date time as a String.
     *
     * @param dateTime the date to be formatted.
     * @return the current date time as a String.
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    /**
     * Returns the localDateTime object of the given date.
     *
     * @param dateTime the date to be parsed must be a valid dateTime.
     * @return the localDateTime object of the given date.
     */
    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, FORMATTER);
    }

}
