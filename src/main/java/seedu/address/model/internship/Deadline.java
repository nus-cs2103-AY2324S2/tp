package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an internship's deadline in the internship book
 */
public class Deadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Deadline must be in the form DD/MM/YYYY, and be a valid date.";

    /**
     * The date format of the deadline.
     */
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
        try {
            LocalDate date = LocalDate.parse(test, formatter);
            System.out.println(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
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
