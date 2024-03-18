package seedu.address.model.person.exceptions;

import seedu.address.model.tag.Tag;

/**
 * Signals that the operation is unable to find the tag on the specified person.
 */

public class TagDoesNotExistException extends RuntimeException {
    public TagDoesNotExistException(Tag tag) {
        super("The tag : " + tag.toString() + " does not exist in the person");
    }
}
