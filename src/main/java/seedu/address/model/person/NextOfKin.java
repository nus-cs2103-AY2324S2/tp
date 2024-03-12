package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's next of kin in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNextOfKin(String)}
 */
public class NextOfKin {
    public static final String MESSAGE_CONSTRAINTS = "Next of kins can take any values, and it should not be blank";

    /*
     * The first character of the next of kin must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code NextOfKin}.
     *
     * @param nextOfKin A valid next of kin.
     */
    public NextOfKin(String nextOfKin) {
        requireNonNull(nextOfKin);
        checkArgument(isValidNextOfKin(nextOfKin), MESSAGE_CONSTRAINTS);
        value = nextOfKin;
    }

    /**
     * Returns true if a given string is a valid next of kin.
     */
    public static boolean isValidNextOfKin(String test) {
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
        if (!(other instanceof NextOfKin)) {
            return false;
        }

        NextOfKin otherNextOfKin = (NextOfKin) other;
        return value.equals(otherNextOfKin.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
