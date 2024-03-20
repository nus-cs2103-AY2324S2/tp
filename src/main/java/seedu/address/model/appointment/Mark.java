package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note in the appointment
 * Guarantees: immutable; note is valid as declared in {@link #isValidNote(String)}
 */
public class Mark {
    public static final String MESSAGE_CONSTRAINTS =
            "Mark should only be true or false, and it should not be blank";

    public final boolean mark;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note.
     */
    public Mark(String mark) {
        requireNonNull(mark);
        checkArgument(isValidMark(mark), MESSAGE_CONSTRAINTS);
        this.mark = Boolean.parseBoolean(mark);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidMark(String test) {
        if (!test.equalsIgnoreCase("true") && !test.equalsIgnoreCase("false")) {
            return false;
        }
        return true;
    }

    /**
     * Returns current boolean value
     */

    public boolean isMarked() {
        return mark;
    }

    @Override
    public String toString() {
        return Boolean.toString(mark);
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
        return (mark == otherMark.mark);
    }

    @Override
    public int hashCode() {
        return Boolean.hashCode(mark);
    }

}
