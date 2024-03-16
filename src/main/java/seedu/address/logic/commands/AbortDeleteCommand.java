package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandBoxState;
import seedu.address.model.Model;

/**
 * Aborts the delete command.
 */
public class AbortDeleteCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Deletion aborted";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, CommandBoxState.NORMAL);
    }
}
