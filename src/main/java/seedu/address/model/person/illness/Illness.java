package seedu.address.model.person.illness;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Illness in the patient book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidIllnessName(String)} (String)}
 */
public class Illness {
    public static final String MESSAGE_CONSTRAINTS = "Illness names should be alphanumeric";
    public static final String VALIDATION_REGEX = "^[A-Za-z0-9 ]+$";
    public final String illnessName;

    /**
     * Constructs a {@code Illness}.
     *
     * @param illnessName A valid illness name.
     */
    public Illness(String illnessName) {
        requireNonNull(illnessName);
        checkArgument(isValidIllnessName(illnessName), MESSAGE_CONSTRAINTS);
        this.illnessName = illnessName;
    }

    /**
     * Returns true if a given string is a valid illness name.
     */
    public static boolean isValidIllnessName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Illness)) {
            return false;
        }

        Illness otherIllness = (Illness) other;
        return illnessName.equals(otherIllness.illnessName);
    }

    @Override
    public int hashCode() {
        return illnessName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + illnessName + ']';
    }

}
