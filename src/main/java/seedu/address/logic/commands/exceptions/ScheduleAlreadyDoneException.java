package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class ScheduleAlreadyDoneException extends Exception {
    /**
     * Constructs a new {@code ScheduleAlreadyDoneException}
     * with the specified detail {@code message} and {@code cause}.
     */
    public ScheduleAlreadyDoneException(String message) {
        super(message);
    }
}
