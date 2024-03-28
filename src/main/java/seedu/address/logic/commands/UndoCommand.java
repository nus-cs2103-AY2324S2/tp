package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the latest modifying command
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the latest modifying command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undid the latest modifying command.";
    public static final String MESSAGE_UNDO_EXCEPTION = "No modifying command to reverse.";

    @Override
    public String execute(Model model) throws CommandException {
        if (!model.canUndo()) {
            throw new CommandException(MESSAGE_UNDO_EXCEPTION);
        }

        model.undo();
        return MESSAGE_SUCCESS;
    }

}
