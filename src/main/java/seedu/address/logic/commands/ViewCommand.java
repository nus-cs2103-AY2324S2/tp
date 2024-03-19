package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a client. \n"
            + "Parameters: \n"
            + "index - Index shown in the corresponding contact list\n"
            + "Example: " + COMMAND_WORD + " 2";

    public static final String MESSAGE_SUCCESS = "Success message: ViewCommand";
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
