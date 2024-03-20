package seedu.address.logic;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;

/**
 * Confirmation to prevent critical actions from being executed accidentally.
 */
public class Confirmation {
    private boolean isToProceed;
    public static final String CONFIRMATION_MESSAGE = "Are you sure that you want to proceed with that action? " +
            "[y/N]";

    public Confirmation(Command command) {
        this.isToProceed = !hasConfirmation(command);
    }

    public boolean hasConfirmation(Command command) {
        return command instanceof AddCommand ||
            command instanceof DeleteCommand;
    }

    public boolean isToProceed() {
        return isToProceed;
    }

    @Override
    public String toString() {
        return CONFIRMATION_MESSAGE;
    }
}
