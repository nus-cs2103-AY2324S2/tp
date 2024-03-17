package seedu.address.model.task;

public class TaskStatus {
    public boolean status;

    public TaskStatus(boolean taskStatus) {
        status = taskStatus;
    }
    public void setTaskDone() {
        status = true;
    }

    public void setTaskNotDone() {
        status = false;
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
