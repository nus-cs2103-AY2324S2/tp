package seedu.hirehub.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hirehub.logic.CommandBoxState;
import seedu.hirehub.model.Model;

/**
 * Pushes the program into the state to clear the database.
 */
public class InitClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Clearing the database is irreversible. Proceed? (Y/N)";
    public static final String MESSAGE_DATABASE_EMPTY = "The database is empty.";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getAddressBook().getPersonList().isEmpty()) {
            return new CommandResult(MESSAGE_DATABASE_EMPTY, CommandBoxState.NORMAL);
        }
        return new CommandResult(MESSAGE_SUCCESS, CommandBoxState.CLEARCONFIRM);
    }
}
