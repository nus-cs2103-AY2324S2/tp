package seedu.address.logic;

import static seedu.address.logic.Messages.MESSAGE_CONFIRMATION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;

/**
 * Confirmation to prevent critical actions from being executed accidentally.
 */
public class Confirmation {
    private boolean isToProceed;

    public Confirmation(Command command) {
        this.isToProceed = !hasConfirmation(command);
    }

    public boolean hasConfirmation(Command command) {
        return command instanceof DeleteCommand;
    }

    public boolean isToProceed() {
        return isToProceed;
    }

    @Override
    public String toString() {
        return MESSAGE_CONFIRMATION;
    }
}
