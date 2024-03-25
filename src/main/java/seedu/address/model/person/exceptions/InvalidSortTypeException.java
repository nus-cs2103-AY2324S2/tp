package seedu.address.model.person.exceptions;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Signals that the user has typed in an invalid sort method.
 */
public class InvalidSortTypeException extends ParseException {
    public InvalidSortTypeException(String type) {
        super(type + " is not a valid sort type!");
    }
}
