package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

/**
 * Represents a Person's notes in the address book.
 */
public class Notes {

    public final ArrayList<Note> notes;

    /**
     * Constructs an empty {@code Notes}.
     */
    public Notes() {
        this.notes = new ArrayList<>();
    }

    /**
     * Constructs a list of {@code Notes}.
     */
    public Notes(ArrayList<String> stringNotes) {
        ArrayList<Note> notes = new ArrayList<>();
        for (String stringNote: stringNotes) {
            Note note = new Note(stringNote);
            notes.add(note);
        }
        this.notes = notes;
    }

    public int getSize() {
        return notes.size();
    }

    public ArrayList<String> getAsStrings() {
        ArrayList<String> notesAsStrings = new ArrayList<>();
        for (Note note: notes) {
            notesAsStrings.add(note.toString());
        }
        return notesAsStrings;
    }

    /**
     * Adds a note to Notes.
     */
    public void addNote(Note note) {
        requireNonNull(note);
        notes.add(note);
    }

    public void deleteNote(int index) {
        notes.remove(index);
    }

    public boolean isEmpty() {
        return notes.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Note note: notes) {
            result.append(note.toString() + '\n');
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Notes)) {
            return false;
        }

        Notes otherNotes = (Notes) other;
        return notes.equals(otherNotes.notes);
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
