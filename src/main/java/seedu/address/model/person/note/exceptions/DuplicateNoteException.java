package seedu.address.model.person.note.exceptions;

/**
 * Signals that the operation will result in duplicate Notes (Notes are considered duplicates if they have the same
 * appointment date time).
 */
public class DuplicateNoteException extends RuntimeException {
    public DuplicateNoteException() {
        super("Operation would result in duplicate note");
    }
}
