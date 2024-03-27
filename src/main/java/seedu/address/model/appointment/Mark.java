package seedu.address.model.appointment;

/**
 * Represents a Mark in the appointment
 * Guarantees: immutable; isMarked is valid as a declared boolean value
 */
public class Mark {
    public static final String MESSAGE_CONSTRAINTS =
            "Mark should only be true or false, and it should not be blank";

    public final boolean isMarked;

    /**
     * Constructs a {@code Mark}.
     *
     * @param isMarked A valid Mark.
     */
    public Mark(boolean isMarked) {
        this.isMarked = isMarked;
    }

    @Override
    public String toString() {
        return Boolean.toString(isMarked);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Mark)) {
            return false;
        }

        Mark otherMark = (Mark) other;
        return (isMarked == otherMark.isMarked);
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(isMarked);
    }

}
