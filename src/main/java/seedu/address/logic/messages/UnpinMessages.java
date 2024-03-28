package seedu.address.logic.messages;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user unpin command visible messages.
 */
public class UnpinMessages {
    public static final String MESSAGE_UNPIN_PERSON_SUCCESS = "Woof! Unpinned %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_UNPIN_INVALID_NAME = "Failed to unpin Pooch Contact. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_UNPIN_NAME_NOT_FOUND = "Failed to unpin Pooch Contact - "
            + "Name does not exist in our address book \uD83D\uDC3E";
    public static final String MESSAGE_UNPIN_MISSING_NAME = "Failed to unpin Pooch Contact - "
            + "Unpin requires a name field. %1$s \uD83D\uDC3E";

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
            builder.append("Maintainer ");
        } else {
            builder.append("Other Contact ");
        }
        builder.append(person.getName());
        return builder.toString();
    }
}
