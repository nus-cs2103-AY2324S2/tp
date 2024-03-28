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

    /**
     * Checks if command requires comfirmation before executing.
     *
     * @param command The command to be executed.
     * @return True if command requires confirmation. False if otherwise.
     */
    public boolean hasConfirmation(Command command) {
        return command instanceof DeleteCommand;
    }

    /**
     * Whether the confirmation is successful or not.
     *
     * @return True if the command can be executed. False if otherwise.
     */
    public boolean isToProceed() {
        return isToProceed;
    }

    @Override
    public String toString() {
        return MESSAGE_CONFIRMATION;
    }
}
