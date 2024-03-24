package seedu.address.logic.messages;

/**
 * Container for user help command visible messages.
 */
public class HelpMessages extends Messages {
    public static final String MESSAGES_SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String MESSAGES_SHOWING_DELETE_HELP_MESSAGE = "Opened help window for delete command.";
    public static final String MESSAGES_SHOWING_EDIT_HELP_MESSAGE = "Opened help window for edit command.";
    public static final String MESSAGES_SHOWING_ADD_HELP_MESSAGE =
            "Opened help window for add command.";
    public static final String MESSAGES_SHOWING_SEARCH_HELP_MESSAGE =
            "Opened help window for search command.";
    public static final String MESSAGES_INVALID_COMMAND_TYPE = "Invalid command type given.";

    public static final String MESSAGE_HELP_MISSING_COMMAND = "Failed to give help - "
            + "Delete requires a command field. \uD83D\uDC3E";
}
