package seedu.address.model.order;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a status of an order in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStatus(String)}
 */
public enum Status {
    PENDING("Pending"),
    ARRIVED("Arrived"),
    LATE("Late");

    public static final String MESSAGE_CONSTRAINTS = "Status should be one of 'Pending', 'Arrived', or 'Late'";

    private final String status;

    /**
     * Constructs a {@code Status}.
     *
     * @param status A valid status.
     */
    Status(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = status;
    }

    /**
     * Returns true if a given string is a valid status.
     */
    public static boolean isValidStatus(String test) {
        return test.equals("Pending") || test.equals("Arrived") || test.equals("Late");
    }


    @Override
    public String toString() {
        return status;
    }
}
