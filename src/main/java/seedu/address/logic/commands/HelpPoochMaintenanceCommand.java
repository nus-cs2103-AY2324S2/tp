package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpPoochMaintenanceCommand extends Command {

    public static final String COMMAND_WORD = "help-poochmaintenance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions"
            + " for pooch-poochmaintenance command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window for pooch-maintenance command.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
