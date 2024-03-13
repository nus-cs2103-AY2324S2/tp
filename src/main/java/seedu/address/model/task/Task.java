package seedu.address.model.task;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Task in the address book.
 */
public class Task {
    // Identity fields
    private final TaskName taskName;

    public Task(TaskName name) {
        taskName = name;
    }

    public TaskName getName() {
        return taskName;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("taskName", taskName)
                .toString();
    }
}
