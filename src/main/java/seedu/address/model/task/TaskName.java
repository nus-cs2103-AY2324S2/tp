package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TaskName {

    public static final String MESSAGE_CONSTRAINTS = "Task name can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String taskName;

    /**
     * Constructs an {@code TaskName}.
     *
     * @param description A valid name.
     */
    public TaskName(String name) {
        requireNonNull(name);
        checkArgument(isValidTaskName(name), MESSAGE_CONSTRAINTS);
        taskName = name;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskName)) {
            return false;
        }

        TaskName otherTaskName = (TaskName) other;
        return taskName.equals(otherTaskName.taskName);
    }

    @Override
    public int hashCode() {
        return taskName.hashCode();
    }
}
