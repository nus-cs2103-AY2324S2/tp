package seedu.address.logic.commands.exceptions;

import java.lang.Exception;

public class InvalidClientException extends Exception {
    public InvalidClientException(String message) {
        super(message);
    }
}
