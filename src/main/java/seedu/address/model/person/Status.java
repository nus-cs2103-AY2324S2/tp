package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

/**
 * Represents a Person's status in the address book.
 * Guarantees: immutable;
 */
public class Status {
    /**
     * Represents status of a person.
     */
    public static enum StatusType { HEALTHY, UNWELL, PENDING }
    private static final String MESSAGE_CONSTRAINTS = "Sex should be either F or M.";
    private static final String VALIDATION_REGEX = "^[A-Z]$";
    private final StatusType status;

    /**
     * Constructs a Status instance.
     *
     * @param status
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status == "HEALTHY" ? StatusType.HEALTHY : status == "UNWELL"
                ? StatusType.UNWELL : StatusType.PENDING;
    }

    public static boolean isValidStatus(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    @Override
    public String toString() {
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
        return Objects.hash(status);
    }
}
