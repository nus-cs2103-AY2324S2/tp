package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPreferredName(String)}
 */
public class PreferredName {

    public static final String MESSAGE_CONSTRAINTS =
        "Preferred name should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String preferredName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid preferred name.
     */
    public PreferredName(String name) {
        requireNonNull(name);
        checkArgument(isValidPreferredName(name), MESSAGE_CONSTRAINTS);
        preferredName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPreferredName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return preferredName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PreferredName)) {
            return false;
        }

        PreferredName otherName = (PreferredName) other;
        return preferredName.equals(otherName.preferredName);
    }

    @Override
    public int hashCode() {
        return preferredName.hashCode();
    }

}
