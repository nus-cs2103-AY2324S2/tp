package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's year in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYear(String)}
 */
public class Year {

    public static final String MESSAGE_CONSTRAINTS =
            "Year should only contain a single digit ranging from 1 - 5, and it should not be blank";

    public static final String VALIDATION_REGEX = "[1-5]";

    public final String value;

    /**
     * Constructs a {@code Year}.
     *
     * @param value A valid year.
     */
    public Year(String value) {
        requireNonNull(value);
        checkArgument(isValidYear(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Year)) {
            return false;
        }

        Year otherYear = (Year) other;
        return value.equals(otherYear.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
