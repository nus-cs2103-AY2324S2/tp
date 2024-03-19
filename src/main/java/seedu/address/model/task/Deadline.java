package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(LocalDateTime)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS = "Deadline should be in the format: dd-MM-yyyy HHmm";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline}
     *
     * @param deadline A valid deadline.
     */
    public Deadline(LocalDateTime deadline) {
        requireNonNull(deadline);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.dateTime = deadline;
    }

    /**
     * Returns true if the deadline is in the correct format.
     */
    public static boolean isValidDeadline(LocalDateTime deadline) {
        try {
            String formattedDeadline = deadline.format(formatter);
            LocalDateTime parsedDeadline = LocalDateTime.parse(formattedDeadline, formatter);
            return deadline.equals(parsedDeadline);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return dateTime.format(formatter);
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

        Deadline otherName = (Deadline) other;
        return dateTime.equals(otherName.dateTime);
    }
}
