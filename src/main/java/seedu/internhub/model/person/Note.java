package seedu.internhub.model.person;

import static seedu.internhub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Note {
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Notes can take any values, and it should not be blank";
    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param note A valid name.
     */
    public Note(String note) {
        if (note.isEmpty()) {
            value = "";
        } else {
            checkArgument(isValidNote(note), MESSAGE_CONSTRAINTS);
            value = note;
        }
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
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
