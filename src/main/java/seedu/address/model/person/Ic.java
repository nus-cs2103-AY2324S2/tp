package seedu.address.model.person;

/**
 * Represents a patient's IC in the address book.
 */
public class Ic {
    public static final String MESSAGE_CONSTRAINTS = "ICs can take integer values only, and it should not be blank";

    public final Integer ic;

    /**
     * Constructs a {@code Ic}.
     *
     * @param ic A valid IC.
     */
    public Ic(Integer ic) {
        this.ic = ic;
    }

    @Override
    public String toString() {
        return ic.toString();
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
