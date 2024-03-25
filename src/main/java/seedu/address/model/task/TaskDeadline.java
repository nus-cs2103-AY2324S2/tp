package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskDeadline {
    public static final String MESSAGE_CONSTRAINTS =
            "Task deadline should be in dd-MM-yyyy HH:mm format, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final DateTimeFormatter VALIDATION_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public final LocalDateTime taskDeadline;

    /**
     * Constructs an {@code TaskDescription}.
     *
     * @param description A valid description.
     */
    public TaskDeadline() {
        taskDeadline = null;
    }

    /**
     * Constructs an {@code TaskDescription}.
     *
     * @param description A valid description.
     */
    public TaskDeadline(String deadline) {
        requireNonNull(deadline);
        checkArgument(isValidTaskDeadline(deadline), MESSAGE_CONSTRAINTS);
        taskDeadline = LocalDateTime.parse(deadline);
    }

    private static boolean isValidTaskDeadline(String deadline) {
        try {
            VALIDATION_FORMAT.parse(deadline);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return taskDeadline.format(OUTPUT_FORMAT);
    }
}
