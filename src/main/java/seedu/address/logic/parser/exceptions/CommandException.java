package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class CommandException extends IllegalValueException {

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
