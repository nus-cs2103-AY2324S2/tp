package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.UndoMessages;
import seedu.address.model.Model;

/**
 * Undo Command.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "/undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo previous execution. ";


    /**
     * Creates an UndoCommand.
     */
    public UndoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canUndo()) {
            throw new CommandException(UndoMessages.MESSAGE_UNDO_FAIL);
        }

        model.undoAddressBook();
        return new CommandResult(UndoMessages.MESSAGE_UNDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UndoCommand)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
