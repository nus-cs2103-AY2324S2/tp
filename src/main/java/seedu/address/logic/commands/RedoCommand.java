package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RedoMessages;
import seedu.address.logic.messages.UndoMessages;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Adds a person to the address book.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "/redo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Redo previous execution. ";


    /**
     * Creates an AddCommand to add the specified {@code Person}
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
