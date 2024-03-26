package seedu.realodex.logic.commands;

import static seedu.realodex.logic.commands.AddCommand.MESSAGE_ADD_HELP;
import static seedu.realodex.logic.commands.ClearCommand.MESSAGE_CLEAR_HELP;
import static seedu.realodex.logic.commands.DeleteCommand.MESSAGE_DELETE_HELP;
import static seedu.realodex.logic.commands.EditCommand.MESSAGE_EDIT_HELP;
import static seedu.realodex.logic.commands.FilterCommand.MESSAGE_FILTER_HELP;
import static seedu.realodex.logic.commands.ListCommand.MESSAGE_LIST_HELP;

import seedu.realodex.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private final String command;

    public HelpCommand(String command) {
        this.command = command.trim();
    }

    @Override
    public CommandResult execute(Model model) {

        switch (command) {

        case "add":
            return new CommandResult(MESSAGE_ADD_HELP, false, false);

        case "clear":
            return new CommandResult(MESSAGE_CLEAR_HELP, false, false);

        case "delete":
            return new CommandResult(MESSAGE_DELETE_HELP, false, false);

        case "edit":
            return new CommandResult(MESSAGE_EDIT_HELP, false, false);

        case "filter":
            return new CommandResult(MESSAGE_FILTER_HELP, false, false);

        case "list":
            return new CommandResult(MESSAGE_LIST_HELP, false, false);

        default:
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherHelpCommand = (HelpCommand) other;
        return command.equals(otherHelpCommand.command);
    }
}
