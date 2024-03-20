package seedu.address.logic.messages;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user delete command visible messages.
 */
public class EditMessages extends Messages {
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Woof! Edited %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_EDIT_EMPTY_FIELD = "Failed to edit Pooch Contact - "
            + "Field is empty! \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_MISSING_NAME = "Edit requires a name field. \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_INVALID_NAME = "Failed to edit Pooch Contact. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDITING_NAME = "Failed to edit Pooch Contact."
            + "Editing Pooch Contact names is not allowed \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_INVALID_FIELD = "Failed to edit Pooch Contact - "
            + "%1$s \uD83D\uDC3E";
    public static final String MESSAGE_EDIT_NO_DIFFERENCE = "Failed to edit Pooch Contact - "
            + "No difference detected!";
    public static final String MESSAGE_INVALID_EDIT_PERSON = "Name does not exist in our address book \uD83D\uDC3E"
            + "Make sure that you are attempting to edit OTHER.";
    public static final String MESSAGE_INVALID_EDIT_STAFF = "Name does not exist in our address book \uD83D\uDC3E"
            + "Make sure that you are attempting to edit STAFF.";
    public static final String MESSAGE_INVALID_EDIT_MAINTAINER = "Name does not exist in our address book \uD83D\uDC3E"
            + " Make sure that you are attempting to edit MAINTAINER.";
    public static final String MESSAGE_INVALID_EDIT_SUPPLIER = "Name does not exist in our address book \uD83D\uDC3E"
            + " Make sure that you are attempting to edit SUPPLIER.";

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        if (person instanceof Staff) {
            builder.append("Pooch Staff ");
        } else if (person instanceof Supplier) {
            builder.append("Supplier ");
        } else if (person instanceof Maintainer) {
            builder.append("Maintenance Crew ");
        } else {
            builder.append("Other Contact ");
        }
        builder.append(person.getName());
        return builder.toString();
    }
}
