package seedu.address.logic.commands.exceptions;

import java.lang.Exception;

public class InvalidMeetingException extends Exception {
    public InvalidMeetingException(String message) {
        super(message);
    }
}