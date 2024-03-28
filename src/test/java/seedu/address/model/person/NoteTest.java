package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidNote_throwsIllegalArgumentException() {
        String invalidNote = "This is an invalid note *&^%";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {
        // Valid notes
        assertTrue(Note.isValidNote("This is a valid note."));
        assertTrue(Note.isValidNote("Another valid note, with punctuation!"));
        assertTrue(Note.isValidNote("1234"));

        // Invalid notes
        assertFalse(Note.isValidNote(""));
        assertFalse(Note.isValidNote("   "));
        assertFalse(Note.isValidNote("Invalid note with symbols *&^%$"));
    }

    @Test
    public void equals() {
        Note note1 = new Note("Note 1");
        Note note2 = new Note("Note 2");

        // Same note should be equal
        assertTrue(note1.equals(note1));

        // Different objects with same note should be equal
        assertTrue(note1.equals(new Note("Note 1")));

        // Different notes should not be equal
        assertFalse(note1.equals(note2));
    }
}
