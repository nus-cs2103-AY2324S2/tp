package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's availability in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAvailability(String)}
 */
public class Availability {


    public static final String MESSAGE_CONSTRAINTS =
            "Module code should contain 2-4 capital letters followed by 4 digits long and at most 1 capitalised suffix";
    public static final String VALIDATION_REGEX = "/((mon|tues|wed(nes)?|thur(s)?|fri|sat(ur)?|sun)(day)?)/gi";

    public final String value;

    /**
     * Constructs a {@code Availability}.
     *
     * @param module A valid availability.
     */
    public Availability(String module) {
        requireNonNull(module);
        checkArgument(isValidAvailability(module), MESSAGE_CONSTRAINTS);
        value = module;
    }

    /**
     * Returns true if a given string is a valid availability.
     */
    public static boolean isValidAvailability(String test) {
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
        if (!(other instanceof Availability)) {
            return false;
        }

        Availability otherAvailability = (Availability) other;
        return value.equals(otherAvailability.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
