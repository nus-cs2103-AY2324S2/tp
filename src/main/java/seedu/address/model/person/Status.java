package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable;
 */
public class Status {

    public static final String MESSAGE_CONSTRAINTS =
            "Status should only be one of the following: HEALTHY, UNWELL, PENDING.";

    /**
     * Represents status of a person.
     */
    public enum StatusType { HEALTHY, UNWELL, PENDING }
    private final StatusType status;

    /**
     * Constructs a Status instance.
     *
     * @param status Status of a person
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = StatusType.valueOf(status);
    }

    /**
     * Gets status type
     *
     * @return Status type of this status
     */
    public StatusType getStatusType() {
        return this.status;
    }

    /**
     * Checks if a String matches the Enum
     * @param testString String of input
     * @return Boolean
     */
    public static boolean isValidStatus(String testString) {
        try {
            StatusType statusType = StatusType.valueOf(testString);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    @Override
    public String toString() {
        // TODO Implement Custom toString format
        return this.status.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Status)) {
            return false;
        }

        Status otherSex = (Status) other;
        return status.equals(otherSex.status);
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }
}
