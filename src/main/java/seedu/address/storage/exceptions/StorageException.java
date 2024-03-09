package seedu.address.storage.exceptions;

/**
 * Represents a storage error encountered by Storage.
 */
public class StorageException extends Exception {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
