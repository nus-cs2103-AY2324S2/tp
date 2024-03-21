package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Opens the meetings window.
 */
public class MeetingsCommand extends Command {
    public static final String COMMAND_WORD = "meetings";
    public static final String SHOWING_MEETINGS_MESSAGE = "Opened meetings window.";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(SHOWING_MEETINGS_MESSAGE, false, true, false);
    }
}
