package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline in the task list.
 */
public class TaskDeadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Task deadline should be in dd-MM-yyyy HH:mm format, and it should not be blank";
    public static final String EMPTY_DEADLINE = "Empty";

    /*
     * The first character of the deadline must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final DateTimeFormatter VALIDATION_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public final LocalDateTime taskDeadline;

    private boolean isEmpty;

    /**
     * Constructs an {@code TaskDeadline}.
     */
    public TaskDeadline() {
        taskDeadline = null;
        isEmpty = true;
    }

    /**
     * Constructs an {@code TaskDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public TaskDeadline(String deadline) {
        requireNonNull(deadline);
        if (deadline.equals(EMPTY_DEADLINE)) {
            taskDeadline = null;
            isEmpty = true;
        } else {
            checkArgument(isValidTaskDeadline(deadline), MESSAGE_CONSTRAINTS);
            taskDeadline = LocalDateTime.parse(deadline, VALIDATION_FORMAT);
            isEmpty = false;
        }
    }

    /**
     * Returns true if a given string is a valid deadline.
     */
    public static boolean isValidTaskDeadline(String deadline) {
        if (deadline.equals(EMPTY_DEADLINE)) {
            return true;
        }

        try {
            VALIDATION_FORMAT.parse(deadline);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty) {
            return "";
        } else {
            return taskDeadline.format(OUTPUT_FORMAT);
        }
    }

    /**
     * Returns a string to save to json file.
     */
    public String toJsonSave() {
        if (isEmpty) {
            return EMPTY_DEADLINE;
        } else {
            return taskDeadline.format(VALIDATION_FORMAT);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDeadline)) {
            return false;
        }

        TaskDeadline otherTaskDeadline = (TaskDeadline) other;
        return this.toJsonSave().equals(otherTaskDeadline.toJsonSave());
    }

    @Override
    public int hashCode() {
        if (isEmpty) {
            return EMPTY_DEADLINE.hashCode();
        } else {
            return taskDeadline.hashCode();
        }
    }
}
