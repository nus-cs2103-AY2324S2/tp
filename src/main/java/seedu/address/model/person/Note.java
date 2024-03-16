package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's note in the address book.
 * Guarantees: immutable; is always valid.
 */
public class Note extends Attribute<String> {

    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";

    /**
     * Constructs a {@code note}.
     *
     * @param note A note.
     */
    public Note(String note) {
        super(note);
        requireNonNull(note);
    }

    /**
     * Determine if the note value stored is a match with a specified string.
     * Returns true if specified value is a substring of the note value stored.
     *
     * @param otherValue Other value to check against
     *
     * @return True if specified value is a match, False otherwise
     */
    @Override
    public boolean isMatch(Object otherValue) {
        if (!(otherValue instanceof String)) {
            return false;
        }

        String other = (String) otherValue;

        return this.getValue().toLowerCase().contains(other.toLowerCase());
    }

    @Override
    public String toString() {
        return this.getValue();
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
        return this.getValue().equals(otherNote.getValue());
    }

    @Override
    public int hashCode() {
        return this.getValue().hashCode();
    }
}
