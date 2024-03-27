package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.ui.ViewMode;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isShowHelp;

    /** The application should exit. */
    private final boolean isExit;

    /** Specifies the viewing mode that the application should be in after command execution. */
    private final ViewMode viewMode;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp,
                         boolean exit, ViewMode viewMode) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isShowHelp = showHelp;
        this.isExit = exit;
        this.viewMode = viewMode;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code isOverallCommand},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, ViewMode viewMode) {
        this(feedbackToUser, false, false, viewMode);
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, ViewMode.ANY);
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

    public ViewMode getViewMode() {
        return viewMode;
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
                && isShowHelp == otherCommandResult.isShowHelp
                && isExit == otherCommandResult.isExit
                && viewMode == otherCommandResult.viewMode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isShowHelp, isExit, viewMode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("feedbackToUser", feedbackToUser)
                .add("isShowHelp", isShowHelp)
                .add("isExit", isExit)
                .add("viewMode", viewMode)
                .toString();
    }

}
