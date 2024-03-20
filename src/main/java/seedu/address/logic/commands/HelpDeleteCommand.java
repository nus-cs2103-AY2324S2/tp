package seedu.address.logic.commands;

import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpDeleteCommand extends Command {
    public static final String COMMAND_WORD = "/help-delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions for delete command.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE, true, false);
    }
}
