package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Major in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {
    public static final String MESSAGE_CONSTRAINTS =
            "Major should only contain alphabetic characters, and it should not be blank";
    public static final String VALIDATION_REGEX = "^[a-zA-Z\\s]+$";

    public final String major;

    /**
     * Constructs a {@code Major}.
     *
     * @param major A valid Major.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        this.major = major;
    }

    public static boolean isValidMajor (String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.major;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Major)) {
            return false;
        }

        Major otherMajor = (Major) other;
        return this.major.equalsIgnoreCase(otherMajor.major);
    }

    @Override
    public int hashCode() {
        return this.major.hashCode();
    }
}
