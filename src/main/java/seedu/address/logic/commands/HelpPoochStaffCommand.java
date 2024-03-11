package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpPoochStaffCommand extends Command {

    public static final String COMMAND_WORD = "help-poochstaff";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows program usage instructions for pooch-staff command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window for pooch-staff command.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
