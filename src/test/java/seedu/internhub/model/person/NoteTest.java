package seedu.internhub.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void isValidNote() {
        // null address
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid addresses
        assertFalse(Note.isValidNote("")); // empty string
        assertFalse(Note.isValidNote(" ")); // spaces only

        // valid addresses
        assertTrue(Note.isValidNote("Yay Yay Yay"));
        assertTrue(Note.isValidNote("-")); // one character
        assertTrue(Note.isValidNote("The CEO of XYZ Corporation, John Smith, is widely recognized ..."));

    }

    @Test
    public void equals() {
        Note note = new Note("Valid Note");

        // same values -> returns true
        assertTrue(note.equals(new Note("Valid Note")));

        // same object -> returns true
        assertTrue(note.equals(note));

        // null -> returns false
        assertFalse(note.equals(null));

        // different types -> returns false
        assertFalse(note.equals(5.0f));

        // different values -> returns false
        assertFalse(note.equals(new Note("Other Valid Note")));
    }
}
