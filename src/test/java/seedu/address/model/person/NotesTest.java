package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NotesTest {
    @Test
    public void constructor_emptyNotes_correctSizeAndEmpty() {
        Notes notes = new Notes();
        assertEquals(0, notes.getSize());
        assertTrue(notes.isEmpty());
    }

    @Test
    public void constructor_notesList_correctSizeAndContent() {
        ArrayList<String> stringNotes = new ArrayList<>(Arrays.asList("Note 1", "Note 2", "Note 3"));
        Notes notes = new Notes(stringNotes);
        assertEquals(3, notes.getSize());
        assertEquals(stringNotes, notes.getAsStrings());
    }

    @Test
    public void addNote_validNote_noteAdded() {
        Notes notes = new Notes();
        Note note = new Note("Test Note");
        notes.addNote(note);
        assertEquals(1, notes.getSize());
        assertEquals("Test Note", notes.getAsStrings().get(0));
    }

    @Test
    public void deleteNote_validIndex_noteDeleted() {
        ArrayList<String> stringNotes = new ArrayList<>(Arrays.asList("Note 1", "Note 2", "Note 3"));
        Notes notes = new Notes(stringNotes);
        notes.deleteNote(1);
        assertEquals(2, notes.getSize());
        assertEquals("Note 1", notes.getAsStrings().get(0));
        assertEquals("Note 3", notes.getAsStrings().get(1));
    }

    @Test
    public void isEmpty_emptyNotes_returnsTrue() {
        Notes notes = new Notes();
        assertTrue(notes.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyNotes_returnsFalse() {
        ArrayList<String> stringNotes = new ArrayList<>(Arrays.asList("Note 1", "Note 2", "Note 3"));
        Notes notes = new Notes(stringNotes);
        assertFalse(notes.isEmpty());
    }

    @Test
    public void toString_validNotes_correctStringRepresentation() {
        ArrayList<String> stringNotes = new ArrayList<>(Arrays.asList("Note 1", "Note 2", "Note 3"));
        Notes notes = new Notes(stringNotes);
        assertEquals("Note 1\nNote 2\nNote 3\n", notes.toString());
    }

    @Test
    public void equals_sameNotes_returnsTrue() {
        ArrayList<String> stringNotes = new ArrayList<>(Arrays.asList("Note 1", "Note 2", "Note 3"));
        Notes notes1 = new Notes(stringNotes);
        Notes notes2 = new Notes(stringNotes);
        assertTrue(notes1.equals(notes2));
    }

    @Test
    public void equals_differentNotes_returnsFalse() {
        ArrayList<String> stringNotes1 = new ArrayList<>(Arrays.asList("Note 1", "Note 2", "Note 3"));
        ArrayList<String> stringNotes2 = new ArrayList<>(Arrays.asList("Note 1", "Note 2"));
        Notes notes1 = new Notes(stringNotes1);
        Notes notes2 = new Notes(stringNotes2);
        assertFalse(notes1.equals(notes2));
    }
}
