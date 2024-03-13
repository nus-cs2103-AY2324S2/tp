package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpEditCommand extends Command {

    public static final String COMMAND_WORD = "help-edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions for edit command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window for edit command.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
