package seedu.address.commons.core.date;

//import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a date
 */
public class Date {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format " + DATE_FORMAT;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public final LocalDate value;

    /**
     * Creates a Date Object
     * @param dateString Date string that has to satisfy validation requirements
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(dateString);
    }

    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String dateString) {
        try {
            LocalDate.parse(dateString, DATE_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns if date is before today
     */
    public static boolean isBeforeToday(String date) {
        return LocalDate.parse(date, DATE_FORMATTER).isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return this.value.format(DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return this.value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
