package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.Task;

/**
 * Mark a task from the task list as undone.
 */
public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmarktask";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a task from the task list as undone. \n"
            + "Parameter: INDEX (Must be a positive integer) \n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Task has been mark as undone: %1$s";

    private final Index targetIndex;

    public UnmarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getTaskList().getSerializeTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownList.get(targetIndex.getZeroBased());
        taskToMark.setAsUndone();

        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, Messages.formatTask(taskToMark)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkTaskCommand)) {
            return false;
        }

        UnmarkTaskCommand otherUnmarkTaskCommand = (UnmarkTaskCommand) other;
        return targetIndex.equals(otherUnmarkTaskCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
