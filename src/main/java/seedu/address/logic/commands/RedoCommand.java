package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RedoMessages;
import seedu.address.model.Model;

/**
 * Redo Command.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "/redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo previous execution. ";


    /**
     * Creates an RedoCommand.
     */
    public RedoCommand() {
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.canRedo()) {
            throw new CommandException(RedoMessages.MESSAGE_REDO_FAIL);
        }

        model.redoAddressBook();
        return new CommandResult(RedoMessages.MESSAGE_REDO_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RedoCommand)) {
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
