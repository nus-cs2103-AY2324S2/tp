package seedu.address.commons.core.date;

import java.time.format.DateTimeParseException;

public class Date {
    private static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final String MESSAGE_CONSTRAINTS =
            "Birthday should be in the format YYYY-MM-DD";
    public final String value;

    public Date(String dateString) {
        if (!isValidDate(dateString)){
            throw new DateTimeParseException(MESSAGE_CONSTRAINTS, dateString, 0);
        }
        this.value = dateString;
    }

    private boolean isValidDate(String dateString) {
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

        Date otherDate= (Date) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
