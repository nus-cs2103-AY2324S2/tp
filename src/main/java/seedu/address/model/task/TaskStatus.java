package seedu.address.model.task;

/**
 * Represents a Task's status in the task list.
 */
public class TaskStatus {
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
        if (status.equalsIgnoreCase("Done")) {
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
            return "Done";
        } else {
            return "Not Done";
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
