package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship Contact's contactNumber in the internship book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContactNumber(String)}
 */

public class ContactNumber {
    public static final String MESSAGE_CONSTRAINTS =
            "ContactNumber should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code ContactNumber}.
     *
     * @param contactNumber A valid contactNumber.
     */
    public ContactNumber(String contactNumber) {
        requireNonNull(contactNumber);
        checkArgument(isValidContactNumber(contactNumber), MESSAGE_CONSTRAINTS);
        value = contactNumber;
    }

    /**
     * Returns true if a given string is a valid contactNumber.
     */
    public static boolean isValidContactNumber(String test) {
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
        if (!(other instanceof seedu.address.model.internship.ContactNumber)) {
            return false;
        }

        seedu.address.model.internship.ContactNumber otherContactNumber =
                (seedu.address.model.internship.ContactNumber) other;
        return value.equals(otherContactNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

