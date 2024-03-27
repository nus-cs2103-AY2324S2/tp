package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** The application should switch between the overall and day view*/
    private final boolean isSwitchView;

    /** The application should switch back to overall view */
    private final boolean isOverallCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isOverallCommand, boolean showHelp,
                         boolean exit, boolean showDayView) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isOverallCommand = isOverallCommand;
        this.isShowHelp = showHelp;
        this.isExit = exit;
        this.isSwitchView = showDayView;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code isOverallCommand},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, boolean isOverallCommand) {
        this(feedbackToUser, isOverallCommand, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isShowHelp;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isSwitchView() {
        return isSwitchView;
    }

    public boolean isOverallCommand() {
        return isOverallCommand;
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
                && isOverallCommand == otherCommandResult.isOverallCommand
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && isSwitchView == otherCommandResult.isSwitchView;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isOverallCommand, isShowHelp, isExit, isSwitchView);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("isOverallCommand", isOverallCommand)
                .add("isShowHelp", isShowHelp)
                .add("isExit", isExit)
                .add("isSwitchView", isSwitchView)
                .toString();
    }

}
