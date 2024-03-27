package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's note in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Note {

    public static final String MESSAGE_CONSTRAINTS =
            "Notes should only contain alphanumeric characters and punctuation(.,!?),"
                    + " and it should not be blank";

    public static final String VALIDATION_REGEX = "^(?=.*[a-zA-Z0-9.,!?])[a-zA-Z0-9.,!? ]+$";

    private final String note;

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
     * Returns true if a given string is a note.
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
