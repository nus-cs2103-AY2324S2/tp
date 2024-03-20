package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Note {
    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param note A valid name.
     */
    public Note(String note) {
        requireNonNull(note);
        value = note;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return true;
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
