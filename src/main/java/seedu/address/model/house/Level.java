package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a House's level.
 * Guarantees: immutable; is valid as declared in {@link #isValidLevel(String)}
 */
public class Level {

    public static final String MESSAGE_CONSTRAINTS =
            "Level should only contain numbers, and it should only be at most 2 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public static final String ZERO_REGEX = "^0+$";

    public final String value;

    /**
     * Constructs a {@code Level}.
     *
     * @param level A valid level.
     */
    public Level(String level) {
        requireNonNull(level);
        checkArgument(isValidLevel(level), MESSAGE_CONSTRAINTS);
        value = level;
    }

    /**
     * Returns true if a given string is a valid level.
     */
    public static boolean isValidLevel(String test) {
        return test.matches(VALIDATION_REGEX) && !test.matches(ZERO_REGEX);
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
        if (!(other instanceof Level)) {
            return false;
        }

        Level otherLevel = (Level) other;
        return value.equals(otherLevel.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
