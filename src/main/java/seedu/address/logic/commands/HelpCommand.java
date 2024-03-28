package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the link to the user guide.\n"
            + "Example: " + COMMAND_WORD;

    public static final String RESULT_MESSAGE = "Successfully opened help window. "
            + "\nAccess the user guide to see all the usable commands.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(RESULT_MESSAGE, true, false);
    }
}
