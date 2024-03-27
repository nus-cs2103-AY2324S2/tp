package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's status in the task list.
 */
public class TaskStatus {
    public static final String MESSAGE_CONSTRAINTS =
            "Task status should be either Done or Not Done";

    private static final String STATUS_DONE = "Done";
    private static final String STATUS_NOTDONE = "Not Done";

    private boolean taskStatus;

    /**
     * Constructs an {@code TaskStatus}.
     */
    public TaskStatus() {
        taskStatus = false;
    }

    /**
     * Constructs an {@code TaskStatus}.
     * @param name A valid status from json.
     */
    public TaskStatus(String status) {
        requireNonNull(status);
        if (status.equals(STATUS_DONE)) {
            taskStatus = true;
        } else if (status.equals(STATUS_NOTDONE)) {
            taskStatus = false;
        } else {
            checkArgument(false, MESSAGE_CONSTRAINTS);
        }
    }

    public boolean getTaskStatus() {
        return taskStatus;
    }

    public void setAsDone() {
        taskStatus = true;
    }

    public void setAsUndone() {
        taskStatus = false;
    }

    @Override
    public String toString() {
        if (taskStatus) {
            return STATUS_DONE;
        } else {
            return STATUS_NOTDONE;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskStatus)) {
            return false;
        }

        TaskStatus otherStatus = (TaskStatus) other;
        return taskStatus == otherStatus.taskStatus;
    }
}
