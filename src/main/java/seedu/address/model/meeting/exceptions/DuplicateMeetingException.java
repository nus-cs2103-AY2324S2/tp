package seedu.address.model.meeting.exceptions;

/**
 * Signals that the operation will result in duplicate Meetings.
 */
public class DuplicateMeetingException extends RuntimeException {
    public DuplicateMeetingException() {
        super("Operation would result in duplicate meetings.");
    }
}
