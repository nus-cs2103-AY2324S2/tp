package seedu.address.model.appointment;

/**
 * Represents a Note in the appointment
 * Guarantees: immutable; note is valid as declared in {@link #isValidNote(String)}
 */
public class Mark {
    public static final String MESSAGE_CONSTRAINTS =
            "Mark should only be true or false, and it should not be blank";

    public final boolean isMarked;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note.
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
