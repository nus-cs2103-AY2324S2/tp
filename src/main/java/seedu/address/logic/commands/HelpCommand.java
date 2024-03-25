package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.HelpMessages;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "/help";
    public static final String MESSAGE_USAGE = "Shows program usage instructions.\n"
            + "Example: /help ; command : exit";
    public static final String MESSAGE_CONSTRAINTS = "Only accepts add, clear, delete, edit, exit,"
            + "list, note as valid command type inputs.";
    private String commandType;


    //did not include find as it will be removed in the future
    enum CommandTypes {
        GENERAL,
        ADD,
        CLEAR,
        DELETE,
        EDIT,
        EXIT,
        SEARCH,
        LIST,
        NOTE
    }

    /**
     * @param commandType of command to get help for.
     */
    public HelpCommand(String commandType) {
        requireAllNonNull(commandType);
        this.commandType = commandType;
    }

    /**
     * Checks if command provided is a valid command.
     *
     * @param commandType command type that user needs help for.
     *
     * @return boolean showing whether the command type is valid.
     * */
    public static boolean isValidCommandType(String commandType) {
        for (CommandTypes c : CommandTypes.values()) {
            if (c.name().toLowerCase().equals(commandType.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (commandType.equals("general")) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_HELP_MESSAGE, true, false);
        } else if (commandType.equals("add")) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_ADD_HELP_MESSAGE, true, false);
        } else if (commandType.equals("delete")) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_DELETE_HELP_MESSAGE, true, false);
        } else if (commandType.equals("edit")) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_EDIT_HELP_MESSAGE, true, false);
        } else if (commandType.equals("search")) {
            return new CommandResult(HelpMessages.MESSAGES_SHOWING_SEARCH_HELP_MESSAGE, true, false);
        } else {
            throw new CommandException(HelpMessages.MESSAGES_INVALID_COMMAND_TYPE);
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
        return commandType.equals(otherHelpCommand.commandType);
    }
}
