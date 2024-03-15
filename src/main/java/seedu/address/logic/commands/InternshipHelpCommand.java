package seedu.address.logic.commands;

import seedu.address.model.InternshipModel;

/**
 * Format full help instructions for every command for display.
 */
public class InternshipHelpCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(InternshipModel model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
