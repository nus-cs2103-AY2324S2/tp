package seedu.address.model.task;

public class TaskStatus {

    public boolean taskStatus;

    /**
     * Constructs an {@code TaskStatus}.
     */
    public TaskStatus() {
        taskStatus = false;
    }

    public void setAsDone() {
        taskStatus = true;
    }

    public void setAsUndone() {
        taskStatus = false;
    }

    @Override
    public String toString() {
        if(taskStatus) {
            return "Task is done";
        } else {
            return "Task is not done";
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
