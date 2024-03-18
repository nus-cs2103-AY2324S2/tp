package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's IC in the address book.
 */
public class Ic {
    public static final String MESSAGE_CONSTRAINTS = "ICs can take integer values only, and it should not be blank";

    // singapore regex for ic
    public static final String VALIDATION_REGEX = "^[A-Z]\\d{7}[A-Z]$";
    public final String value;

    /**
     * Constructs a {@code Ic}.
     *
     * @param value A valid IC.
     */
    public Ic(String value) {
        requireNonNull(value);
        checkArgument(isValidIc(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }
    public static boolean isValidIc(String ic) {
        return ic.matches(VALIDATION_REGEX);
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

        if (!(other instanceof Ic)) {
            return false;
        }

        Ic otherIc = (Ic) other;
        return value.equals(otherIc.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    // Todo: isValidIc
}
