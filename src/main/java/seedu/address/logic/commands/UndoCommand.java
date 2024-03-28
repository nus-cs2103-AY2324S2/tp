package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Undoes the previous command.
 */
public class UndoCommand extends Command {
    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Undoes the previously issued command that changed data."
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undid previous command:\n\t";

    public static final String MESSAGE_CANNOT_UNDO = "Unable to undo since there were no previously issued commands.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.canUndoAddressBook()) {
            model.undoAddressBook();
            String undoneCommandMessage = model.getAddressBookUndoneCommand().getFeedbackToUser();
            return new CommandResult(MESSAGE_SUCCESS + undoneCommandMessage);
        } else {
            throw new CommandException(MESSAGE_CANNOT_UNDO);
        }
    }
}
