package seedu.edulink.logic.commands;

import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;

/**
 * Undo the most recent command and revert back to previous state
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Undo Command done Successfully";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.resetToPreviousState()) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            throw new CommandException(Messages.MESSAGE_NO_HISTORY_FOUND);
        }
    }

}
