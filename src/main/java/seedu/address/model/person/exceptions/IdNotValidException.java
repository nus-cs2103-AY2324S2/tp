package seedu.address.model.person.exceptions;

public class IdNotValidException extends RuntimeException {
    public IdNotValidException(String message) {
        super(message);
    }
}
