package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's NUSNET in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNusNet(String)}
 */
public class NusNet {

    public static final String MESSAGE_CONSTRAINTS = "NUSNET should be of the format E"
        + "followed by 7 digits";
    public static final String VALIDATION_REGEX = "^[Ee]\\d{7}$";

    public final String value;

    /**
     * Constructs an {@code NusNet}.
     *
     * @param nusnet A valid nusnet id.
     */
    public NusNet(String nusnet) {
        requireNonNull(nusnet);
        checkArgument(isValidNusNet(nusnet), MESSAGE_CONSTRAINTS);
        value = nusnet;
    }

    /**
     * Returns if a given string is a valid nusnet id.
     */
    public static boolean isValidNusNet(String test) {
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
        if (!(other instanceof NusNet)) {
            return false;
        }

        NusNet otherNusNet = (NusNet) other;
        return this.value.equalsIgnoreCase(otherNusNet.value); // case insensitive
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
