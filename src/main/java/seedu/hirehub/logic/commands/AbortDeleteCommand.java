package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hirehub.logic.CommandBoxState;
import seedu.hirehub.model.Model;

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
