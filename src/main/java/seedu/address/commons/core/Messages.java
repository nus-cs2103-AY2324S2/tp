package seedu.address.commons.core;

/**
 * Container for user-visible messages in the application.
 *
 * This class holds the definitions of string messages used across the application to communicate
 * with the user, particularly for error handling and command feedback. Centralizing these messages
 * in a single class simplifies the management of user feedback and ensures consistency in the
 * messages presented to the user.
 */

public class Messages {
    /**
     * Message to inform the user that the command entered is invalid.
     * This could be due to incorrect syntax, missing arguments, or unrecognized command keywords.
     */
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid Command";
    /**
     * Message to inform the user that the index provided for a person-related command does not correspond
     * to any entry in the current view.
     * This placeholder message ("???") should be replaced with a more descriptive message indicating
     * the nature of the error and possibly suggesting corrective actions.
     */
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "???";
}
