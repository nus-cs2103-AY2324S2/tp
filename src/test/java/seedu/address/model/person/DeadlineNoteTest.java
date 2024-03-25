package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineNoteTest {

    private String validNote = "careful poochies";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineNote(validNote, null));
    }


    @Test
    public void isValidDate() {
        // null deadlineNotes
        assertThrows(NullPointerException.class, () -> DeadlineNote.isValidDate(null));

        // invalid deadlineNotes
        assertFalse(DeadlineNote.isValidDate("")); // empty string
        assertFalse(DeadlineNote.isValidDate(" ")); // spaces only

        // valid deadlineNotes
        assertTrue(DeadlineNote.isValidDate("2019-10-10"));
    }
    @Test
    public void equals() {
        Note deadlineNote = new DeadlineNote("kind cats", "2025-10-10");

        // same values -> returns true
        assertTrue(deadlineNote.equals(new DeadlineNote("kind cats", "2025-10-10")));

        // same object -> returns true
        assertTrue(deadlineNote.equals(deadlineNote));

        // null -> returns false
        assertFalse(deadlineNote.equals(null));

        // different types -> returns false
        assertFalse(deadlineNote.equals(5.0f));

        // different values -> returns false
        assertFalse(deadlineNote.equals(new DeadlineNote("kind cats", "2345-10-10")));
    }
}
