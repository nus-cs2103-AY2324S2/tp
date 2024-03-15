package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's IC in the address book.
 */
public class Ic {
    public static final String MESSAGE_CONSTRAINTS = "ICs can take integer values only, and it should not be blank";

    // singapore regex for ic
    public static final String VALIDATION_REGEX = "^[STFG]\\d{7}[A-Z]$";
    public final String ic;

    /**
     * Constructs a {@code Ic}.
     *
     * @param ic A valid IC.
     */
    public Ic(String ic) {
        requireNonNull(ic);
        checkArgument(isValidIc(ic), MESSAGE_CONSTRAINTS);
        this.ic = ic;
    }
    public static boolean isValidIc(String ic) {
        return ic.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return ic;
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
        return ic.equals(otherIc.ic);
    }

    @Override
    public int hashCode() {
        return ic.hashCode();
    }

    // Todo: isValidIc
}
