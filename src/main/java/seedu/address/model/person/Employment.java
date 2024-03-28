package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Staff's employment status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmployment(String)} )}
 */
public class Employment {
    public static final String MESSAGE_CONSTRAINTS =
            "Employment should be either full-time or part-time";
    public static final String VALIDATION_REGEX1 = "part-time";
    public static final String VALIDATION_REGEX2 = "full-time";
    public final String value;

    /**
     * Constructs an {@code Employment}.
     *
     * @param value A valid employment either part-time or full-time.
     */
    public Employment(String value) {
        requireNonNull(value);
        checkArgument(isValidEmployment(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidEmployment(String test) {
        return test.matches(VALIDATION_REGEX1) || test.matches(VALIDATION_REGEX2);
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
        if (!(other instanceof Employment)) {
            return false;
        }

        Employment otherEmployment = (Employment) other;
        return value.equals(otherEmployment.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
