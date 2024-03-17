package seedu.address.model.task;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Task in the address book.
 */
public class Task {
    // Identity fields
    private static int universalTaskId = 1;
    private final TaskName taskName;
    private final TaskId taskId;
    private final TaskStatus taskStatus;
    /**
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskId id, TaskStatus status) {
        taskName = name;
        taskId = id;
        taskStatus = status;
    }

    /**
     * Updates Task.TASK_ID when loading from JSON
     */
    public static void setUniversalTaskId(int id) {
        Task.universalTaskId = id;
    }

    public static int getUniversalId() {
        int ret = Task.universalTaskId;
        return ret;
    }
    /**
     * Increments Task.TASK_ID
     */
    public static void incrementTaskId() {
        Task.universalTaskId++;
    }

    public TaskName getName() {
        return taskName;
    }

    public TaskId getTaskId() {
        return taskId;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void markTask() {
        taskStatus.setTaskDone();
    }

    public void unmarkTask() {
        taskStatus.setTaskNotDone();
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("taskId", taskId)
                .add("taskName", taskName)
                .add("taskStatus", taskStatus)
                .toString();
    }
}
