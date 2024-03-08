package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public String execute(Model model) {
        return Messages.MESSAGE_SHOWING_HELP;
    }
}
