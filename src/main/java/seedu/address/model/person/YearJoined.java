package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Person's year joined in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidYearJoined(String)}
 */
public class YearJoined {

    public static final String MESSAGE_CONSTRAINTS =
            "Year joined should only contain numbers and it must be between 2010 to 2099.";

    public static final String VALIDATION_REGEX = "\\d{4}";

    public final int value;

    /**
     * Constructs a {@code YearJoined}.
     *
     * @param yearJoined A valid year joined.
     */
    public YearJoined(String yearJoined) {
        requireAllNonNull(yearJoined);
        checkArgument(isValidYearJoined(yearJoined), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(yearJoined);
    }

    /**
     * Returns true if a given string is a valid year joined.
     */
    public static boolean isValidYearJoined(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int year = Integer.parseInt(test);
            return year >= 2010 && year <= 2099;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof YearJoined)) {
            return false;
        }

        YearJoined otherName = (YearJoined) other;
        return value == otherName.value;
    }
}
