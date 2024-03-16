package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandBoxState;
import seedu.address.model.Model;

/**
 * Pushes the program into the state to clear the database.
 */
public class InitClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Clearing the database is irreversible. Proceed? (Y/N)";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, CommandBoxState.CLEARCONFIRM);
    }
}
