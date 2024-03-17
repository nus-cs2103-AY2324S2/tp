package seedu.address.model.person;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable;
 */
public class Status {
    private enum HealthStatus { GREEN, YELLOW, RED };
    private final String status;

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HealthStatus)) {
            return false;
        }

        Status otherStatus = (Status) other;
        return status.equals(otherStatus.status);
    }
}
