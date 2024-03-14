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

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskId id) {
        taskName = name;
        taskId = id;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("taskId", taskId)
                .add("taskName", taskName)
                .toString();
    }
}
