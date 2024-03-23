package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's Task in the internship book
 */
public class Task {
    public static final String MESSAGE_CONSTRAINTS =
            "Task should not be blank!";

    /*
     * Matches any characters that are not only whitespace
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String task;

    /**
     * Constructs a {@code Task}.
     *
     * @param task A valid task.
     */
    public Task(String task) {
        requireNonNull(task);
        checkArgument(isValidTask(task), MESSAGE_CONSTRAINTS);
        this.task = task;
    }

    /**
     * Returns true if a given string is a valid task.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return task;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherName = (Task) other;
        return task.equals(otherName.task);
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }
}
