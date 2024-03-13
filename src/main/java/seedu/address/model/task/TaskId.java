package seedu.address.model.task;

/**
 * Represents a Task's Id.
 */
public class TaskId {
    public final int taskId;

    /**
     * Constructs a {@code TaskId}.
     *
     * @param id A valid taskId.
     */
    public TaskId(int id) {
        taskId = id;
    }

    @Override
    public String toString() {
        return "" + taskId;
    }
}
