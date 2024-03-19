package seedu.address.model.task;

/**
 * Represents the status of a task, which can be either completed or in progress.
 */
public class TaskStatus {
    private boolean status;

    public TaskStatus(boolean taskStatus) {
        status = taskStatus;
    }
    public void setTaskDone() {
        status = true;
    }
    public void setTaskNotDone() {
        status = false;
    }
    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        if (status) {
            return "Completed";
        } else {
            return "In Progress";
        }
    }
}
