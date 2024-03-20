package seedu.address.model.person;

/**
 * Represents a Person's status in the Tether.
 */
public abstract class Status {
    public abstract String matchStatus(String status, String message);
}
