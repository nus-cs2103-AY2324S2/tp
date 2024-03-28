package seedu.address.model.weeknumber;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Week Number for attendance in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidWeekNumber(String)}
 */
public class WeekNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "Week numbers should be integers between 1 and 13 inclusive.";
    public static final String VALIDATION_REGEX = "^(1[0-3]|[1-9])$";
    public final Integer value;

    /**
     * Constructs a {@code WeekNumber}.
     *
     * @param weekNumber A valid week number.
     */
    public WeekNumber(String weekNumber) {
        requireNonNull(weekNumber);
        checkArgument(isValidWeekNumber(weekNumber), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(weekNumber);
    }

    /**
     * Returns true if a given string is a valid week number.
     */
    public static boolean isValidWeekNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof WeekNumber)) {
            return false;
        }

        WeekNumber otherWeekNumber = (WeekNumber) other;
        return value.equals(otherWeekNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
