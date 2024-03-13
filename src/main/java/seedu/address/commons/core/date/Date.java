package seedu.address.commons.core.date;

//import java.time.format.DateTimeParseException;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a date
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the format YYYY-MM-DD";
    private static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public final String value;

    /**
     * Creates a Date Object
     * @param dateString Date string that has to satisfy validation requirements
     */
    public Date(String dateString) {
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        this.value = dateString;
    }

    public static boolean isValidDate(String dateString) {
        return dateString.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
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
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
