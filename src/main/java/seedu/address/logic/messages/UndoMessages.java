package seedu.address.logic.messages;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user undo command visible messages.
 */
public class UndoMessages extends Messages {
    public static final String MESSAGE_UNDO_SUCCESS = "Woof! Undo successfully! \uD83D\uDC36";
    public static final String MESSAGE_UNDO_FAIL = "Woof! There is no more previous state to undo!";

}
