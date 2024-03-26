package seedu.address.ui;

/**
 * Represents a generic interface for displaying prompts to the user. Implementations of this interface
 * can be used to display various types of messages or dialogs that require user interaction, such as
 * confirmation dialogs, information alerts, or input requests.
 */
public interface Prompt {
    /**
     * Displays a prompt to the user with the given title and message, and captures the user's response.
     *
     * @param title   the title of the prompt to be displayed.
     * @param message the message to be displayed to the user. This is the main content of the prompt, providing
     *                the user with the information or question that requires their interaction.
     * @return a boolean value representing the user's response to the prompt. The interpretation of this response
     *         (e.g., what {@code true} or {@code false} signifies) depends on the implementation and the context
     *         in which the prompt is used.
     */
    boolean display(String title, String message);
}
