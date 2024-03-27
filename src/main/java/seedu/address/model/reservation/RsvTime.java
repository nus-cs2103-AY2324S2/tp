package seedu.address.model.reservation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reservation's reservation time in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRsvTime(String)}
 */
public class RsvTime {

    public static final String MESSAGE_CONSTRAINTS = "Reservation time should be HHmm format";
    public static final String VALIDATION_REGEX = "([01][0-9]|2[0-3])[0-5][0-9]";
    public final String value;

    /**
     * Constructs a {@code RsvTime}.
     *
     * @param rsvTime A valid reservation time.
     */
    public RsvTime(String rsvTime) {
        requireNonNull(rsvTime);
        checkArgument(isValidRsvTime(rsvTime), MESSAGE_CONSTRAINTS);
        this.value = rsvTime;
    }

    /**
     * Returns true if a given string is a valid reservation time of format HHmm.
     */
    public static boolean isValidRsvTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RsvTime)) {
            return false;
        }

        RsvTime otherRsvTime = (RsvTime) other;
        return this.value.equals(otherRsvTime.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
