package seedu.address.model.task;

/**
 * Represents a Task's status in the task list.
 */
public class TaskStatus {
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
     */
    public TaskStatus(String status) {
        if (status.equalsIgnoreCase(STATUS_DONE)) {
            taskStatus = true;
        } else {
            taskStatus = false;
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
