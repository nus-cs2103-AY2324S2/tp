package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's note in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS = "notes can take any values, and it should not be blank";

    public final String value;

    /**
     * Constructs a {@code note}.
     *
     * @param note A note.
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
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
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return value.equals(otherNote.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
