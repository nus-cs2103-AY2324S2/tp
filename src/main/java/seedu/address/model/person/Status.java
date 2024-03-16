package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's recruitment status in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public class Status {
    /**
     * Constructs an enum class containing 5 fixed, different recruitment status
     * for individual candidates.
     */
    enum STATUS {
        PRESCREEN,
        IN_PROGRESS,
        WAITLIST,
        ACCEPTED,
        REJECTED
    }

    public final String value;

    public static final String MESSAGE_CONSTRAINTS = "Interview status must be one of the following values: "
            + "PRESCREEN, IN_PROGRESS, WAITLIST, ACCEPTED, REJECTED";

    /**
     * Constructs an {@code Status}.
     *
     * @param status A valid status.
     */
    public Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        value = status.toUpperCase();
    }

    @Override
    public String toString() {
        return value;
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

        Status otherStatus = (Status) other;
        return value.equals(otherStatus.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if a given string is a valid status; it needs to be one of the
     * values specified in the enum list.
     */
    public static boolean isValidStatus(String status) {
        boolean isValid = false;
        for (STATUS enumValue : STATUS.values()) {
            if (enumValue.name().equals(status.toUpperCase())) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}