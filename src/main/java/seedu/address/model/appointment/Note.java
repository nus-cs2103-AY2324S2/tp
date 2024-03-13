package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note in the appointment
 * Guarantees: immutable; note is valid as declared in {@link #isValidNote(String)}
 */
public class Note {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the notes must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String note;

    /**
     * Constructs a {@code Note}.
     *
     * @param note A valid note.
     */
    public Note(String note) {
        requireNonNull(note);
        checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
        this.note = note;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return note;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return note.equals(otherNote.note);
    }

    @Override
    public int hashCode() {
        return note.hashCode();
    }

}

