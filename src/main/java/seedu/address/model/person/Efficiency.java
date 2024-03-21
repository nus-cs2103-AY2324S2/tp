package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's efficiency in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEfficiency(String)}
 */
public class Efficiency {
    public static final String MESSAGE_CONSTRAINTS =
            "Efficiency should be in the range 0 to 100";
    public static final String VALIDATION_REGEX = "\\b(\\d{1,2}|100)\\b";
    public final String value;

    /**
     * Constructs a {@code Efficiency}.
     *
     * @param efficiency A valid efficiency
     */
    public Efficiency(String efficiency) {
        requireNonNull(efficiency);
        checkArgument(isValidEfficiency(efficiency), MESSAGE_CONSTRAINTS);
        value = efficiency;
    }

    /**
     * Returns true if a given string is a valid efficiency.
     */
    public static boolean isValidEfficiency(String test) {
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
        if (!(other instanceof Efficiency)) {
            return false;
        }

        Efficiency otherEfficiency = (Efficiency) other;
        return value.equals(otherEfficiency.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
