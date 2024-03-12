package seedu.address.commons.util;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Helper functions for handling dates and times.
 */
public class DateTimeUtil {
    public static final String MESSAGE_CONSTRAINTS = "should be in the format YYYY-MM-DD HH:mm " +
            "and must be an actual dateTime";
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}";

    /**
     * Returns true if a given string is a valid dateTime.
     * The string must be in the format "yyyy-MM-dd hh:mm".
     * The dateTime string should be validated as parsable before calling this method.
     *
     * @param test the string to test
     * @return true if the string is a valid date
     */
    public static boolean isValidDateTimeString(String test) {
        return (test != null) && test.matches(VALIDATION_REGEX)
                && isParsableDateTimeString(test);
    }

    /**
     * Returns true if a given string is a parsable dateTime.
     *
     * @param test the string representation of a dateTime to check
     * @return true if the string is a parsable dateTime
     */
    public static boolean isParsableDateTimeString(String test) {
        if (test == null || test.isEmpty()) {
            return false;
        }

        try {
            LocalDateTime.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a given dateTime is in the past.
     *
     * @param dateTime the dateTime to check
     * @return true if the dateTime is in the past
     */
    public static boolean isPastDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return false;
        }
        return dateTime.isBefore(LocalDateTime.now());
    }

    /**
     * Parses a string representation of a dateTime into a LocalDateTime object.
     * The string representation must be in the format "yyyy-MM-dd hh:mm".
     * The dateTime string should be validated as parsable before calling this method.
     *
     * @param dateTime the string representation of a date
     * @return the LocalDate object
     */
    public static LocalDateTime parseStringToDateTime(String dateTime) throws DateTimeParseException {
        if (dateTime == null || dateTime.isEmpty()) {
            throw new DateTimeParseException("Date string is empty", dateTime, 0);
        }
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Parses a LocalDateTime object into a string representation of a dateTime.
     * The string representation will be in the format "YYYY-MM-DD hh:mm".
     * The date should be validated as parsable before calling this method.
     *
     * @param dateTime the LocalDateTtime object
     * @return the string representation of the date
     */
    public static String parseDateToString(LocalDateTime dateTime) throws DateTimeException {
        if (dateTime == null) {
            return "";
        }
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
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