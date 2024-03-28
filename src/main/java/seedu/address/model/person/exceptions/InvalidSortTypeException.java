package seedu.address.model.person.exceptions;

import static seedu.address.logic.Messages.MESSAGE_INVALID_SORT_TYPE;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Signals that the user has typed in an invalid sort method.
 */
public class InvalidSortTypeException extends ParseException {
    public InvalidSortTypeException(String type) {
        super(String.format(MESSAGE_INVALID_SORT_TYPE, type));
    }
}
