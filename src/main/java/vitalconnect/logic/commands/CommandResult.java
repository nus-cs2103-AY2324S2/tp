package vitalconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import vitalconnect.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 * Contains information about the execution result, such as feedback to the user,
 * whether help information should be shown, whether the application should exit,
 * and the type of view to show in the UI.
 */
public class CommandResult {
    /**
     * Represents the type of view to show in the UI.
     */
    public enum Type {
        SHOW_PERSONS,
        SHOW_APPOINTMENTS
    }

    /** Feedback message to be shown to the user. */
    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;
    /** The type of view to show in the UI. */
    private final Type type;


    /**
     * Constructs a {@code CommandResult} with all fields specified.
     *
     * @param feedbackToUser Feedback message to the user.
     * @param showHelp True if help information should be shown.
     * @param exit True if the application should exit.
     * @param type The type of result, indicating which UI view should be shown.
     */
    public CommandResult(String feedbackToUser, boolean showHelp, boolean exit, Type type) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.type = type;
    }

    /**
     * Constructs a {@code CommandResult} with the specified feedback message
     * and default values for other fields.
     *
     * @param feedbackToUser Feedback message to the user.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, Type.SHOW_PERSONS);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("showHelp", showHelp)
                .add("exit", exit)
                .toString();
    }

    public Type getType() {
        return type;
    }

}
