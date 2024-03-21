package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
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


    @Test
    public void isMatch() {
        Note note = new Note("Likes to eat");

        // Exact match -> returns true
        assertTrue(note.isMatch("Likes to eat"));

        // Substring whole word -> returns true
        assertTrue(note.isMatch(" Likes"));

        // Substring partial word -> returns true
        assertTrue(note.isMatch(" Lik"));

        // Substring case mismatch -> returns true
        assertTrue(note.isMatch(" lik"));

        // Additional whitespace
        assertTrue(note.isMatch(" Likes to eat\n"));

        // Substring mismatch
        assertFalse(note.isMatch("run"));

        // Different type
        assertFalse(note.isMatch(1));
    }
}
