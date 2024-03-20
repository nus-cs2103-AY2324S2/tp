package seedu.address.logic;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;

/**
 * Confirmation to prevent critical actions from being executed accidentally.
 */
public class CommandConfirmation {
    public static final String CONFIRMATION_MESSAGE = "Are you sure that you want to proceed with that action? " +
            "[y/N]";

    public CommandConfirmation() {

    }

    public boolean isYes() {
        return true;
    }

    public CommandResult

    public boolean hasConfirmation(Command command) {
        return command instanceof DeleteCommand;
    }

}
