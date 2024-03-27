package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverses the latest undo command.
 * Does not reverse if a modifying command is executed after undo command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Reverse the latest undo command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Reversed the latest undo command.";
    public static final String MESSAGE_REDO_EXCEPTION = "No undo command to reverse.";

    @Override
    public String execute(Model model) throws CommandException {
        if (!model.canRedo()) {
            throw new CommandException(MESSAGE_REDO_EXCEPTION);
        }

        model.redo();
        return MESSAGE_SUCCESS;
    }

}
