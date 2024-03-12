package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class FutureDateException extends Exception {
    /**
     * Constructs a new {@code FutureDateException} with the specified detail {@code message} and {@code cause}.
     */
    public FutureDateException(String message) {
        super(message);
    }
}
