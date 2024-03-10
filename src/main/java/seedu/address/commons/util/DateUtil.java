package seedu.address.commons.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates and times.
 */
public class DateUtil {
    public static final String MESSAGE_CONSTRAINTS = "should be in the format YYYY-MM-DD and must be an actual date";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    /**
     * Returns true if a given string is a valid date.
     * The string must be in the format "yyyy-MM-dd".
     * The date string should be validated as parsable before calling this method.
     *
     * @param test the string to test
     * @return true if the string is a valid date
     */
    public static boolean isValidDateString(String test) {
        return (test != null) && test.matches(VALIDATION_REGEX)
                && isParsableDateString(test);
    }

    /**
     * Returns true if a given string is a parsable date.
     *
     * @param test the string representation of a date to check
     * @return true if the string is a parsable date
     */
    public static boolean isParsableDateString(String test) {
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given date is in the past.
     *
     * @param date the date to check
     * @return true if the date is in the past
     */
    public static boolean isPastDate(LocalDate date) {
        if (date == null) {
            return false;
        }
        return date.isBefore(LocalDate.now());
    }

    /**
     * Parses a string representation of a date into a LocalDate object.
     * The string representation must be in the format "yyyy-MM-dd".
     * The date string should be validated as parsable before calling this method.
     *
     * @param date the string representation of a date
     * @return the LocalDate object
     */
    public static LocalDate parseStringToDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Parses a LocalDate object into a string representation of a date.
     * The string representation will be in the format "YYYY-MM-DD".
     * The date should be validated as parsable before calling this method.
     *
     * @param date the LocalDate object
     * @return the string representation of the date
     */
    public static String parseDateToString(LocalDate date) throws DateTimeException {
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Returns a message constraint that is contextualised to the date type.
     * For example, if the date type is "Birthday", the message constraint will be "Birthday should be in the format.
     *
     * @param dateType the type of date, such as "Birthday", "Appointment"
     * @return a message constraint that is contextualised to the date type
     */
    public static String getMessageConstraintsForDateType(String dateType) {
        return dateType + " " + MESSAGE_CONSTRAINTS;
    }
}
