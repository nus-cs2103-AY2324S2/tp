package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an internship's deadline in the internship book
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline should not be blank!";

    /*
     * Matches any characters that are not only whitespace
     */
    public static final String VALIDATION_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    public final String deadline;

    /**
     * Constructs a {@code Deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = deadline;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidDeadline(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the deadline string.
     */
    @Override
    public String toString() {
        return deadline;
    }

    /**
     * Checks equality based on the deadline string.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherDeadline = (Deadline) other;
        return deadline.equals(otherDeadline.deadline);
    }

    /**
     * Generates hash code based on the deadline string.
     */
    @Override
    public int hashCode() {
        return deadline.hashCode();
    }
}
