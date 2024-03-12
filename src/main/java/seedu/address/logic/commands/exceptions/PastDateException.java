package seedu.address.logic.commands.exceptions;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class PastDateException extends Exception {
    /**
     * Constructs a new {@code PastDateException} with the specified detail {@code message} and {@code cause}.
     */
    public PastDateException(String message) {
        super(message);
    }
}
