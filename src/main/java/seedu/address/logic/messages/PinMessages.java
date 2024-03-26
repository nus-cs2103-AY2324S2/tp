package seedu.address.logic.messages;

import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Container for user pin command visible messages.
 */
public class PinMessages {
    public static final String MESSAGE_PIN_PERSON_SUCCESS = "Woof! Pinned %1$s successfully! \uD83D\uDC36";
    public static final String MESSAGE_PIN_INVALID_NAME = "Failed to pin Pooch Contact. %1$s \uD83D\uDC3E";
    public static final String MESSAGE_PIN_MISSING_NAME = "Failed to pin Pooch Contact - "
            + "Pin requires a name field. %1$s \uD83D\uDC3E";

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
