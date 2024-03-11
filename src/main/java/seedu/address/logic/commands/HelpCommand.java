package seedu.address.logic.commands;

import static seedu.address.logic.commands.util.CommandMessageUsageUtil.generateMessageUsage;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = generateMessageUsage(
            COMMAND_WORD,
            "Shows program usage instructions.",
            COMMAND_WORD
    );

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
