package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Note in the appointment
 * Guarantees: immutable; note can take any value
 */
public class Note {

    public final String note;

    /**
     * Constructs a {@code Note}.
     *
     * @param note Any note.
     */
    public Note(String note) {
        requireNonNull(note);
        this.note = note;
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
