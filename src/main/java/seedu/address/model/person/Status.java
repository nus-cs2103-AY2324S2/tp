package seedu.address.model.person;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable;
 */
public class Status {
    private enum status { HEALTHY, AT_RISK, UNHEALTHY };
    private final String status;

    public Status(String status) {
        this.status = status.valueOf(status);
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return this.status.valueOf(status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Status)) {
            return false;
        }

        Status otherStatus = (Status) other;
        return status.equals(otherStatus.status);
    }
}
