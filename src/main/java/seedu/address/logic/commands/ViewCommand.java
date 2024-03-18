package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Views the Summary Stats of the address book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": shows the summary stats"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Viewing the stats of students";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
