package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class TaskDescription {

    public static final String MESSAGE_CONSTRAINTS = "Task descriptions can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String taskDescription;

    /**
     * Constructs an {@code TaskDescription}.
     *
     * @param description A valid description.
     */
    public TaskDescription(String decription) {
        requireNonNull(decription);
        checkArgument(isValidTaskDescription(decription), MESSAGE_CONSTRAINTS);
        taskDescription = decription;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTaskDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return taskDescription;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDescription)) {
            return false;
        }

        TaskDescription otherTaskDescription = (TaskDescription) other;
        return taskDescription.equals(otherTaskDescription.taskDescription);
    }

    @Override
    public int hashCode() {
        return taskDescription.hashCode();
    }

}
