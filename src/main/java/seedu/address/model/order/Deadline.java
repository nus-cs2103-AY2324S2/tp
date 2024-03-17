package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateTimeUtil;

/**
 * Represents a Deadline that an order must be fulfilled
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}.
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS =
            "A deadline should be in the format of "
                    + "DD-MM-YYYY HH:MM, e.g. 01-01-2024 23:59";


    public final LocalDateTime deadline;

    /**
     * Constructs a {@code deadline}.
     *
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.deadline = DateTimeUtil.parseDateTime(deadline);
    }


    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidDeadline(String test) {
        return DateTimeUtil.isValidDate(test);
    }


    @Override
    public String toString() {
        return deadline.toString();
    }


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

    @Override
    public int hashCode() {
        return deadline.hashCode();
    }

}
