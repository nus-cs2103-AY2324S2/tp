package seedu.address.model.task;

/**
 * The representation of a task added by the user.
 */
public class Task {
    private String description;

    /**
     * The constructor of the class.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Gets the description of a task.
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
}
