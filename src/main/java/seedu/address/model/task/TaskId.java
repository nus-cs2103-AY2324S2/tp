package seedu.address.model.task;

/**
 * Represents a Task's Id.
 */
public class TaskId {
    public final int taskId;

    /**
     * Constructs a {@code TaskId}.
     *
     * @param Id A valid taskId.
     */
    public TaskId(int Id) {
        taskId = Id;
    }

    @Override
    public String toString() {
        return "" + taskId;
    }
}
