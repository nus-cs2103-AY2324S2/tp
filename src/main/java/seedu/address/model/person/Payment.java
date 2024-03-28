package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's payment status in the address book.
 * Guarantees: immutable; is always valid
 */
public class Payment {
    public static final String MESSAGE_CONSTRAINTS =
            "Payment should only be 'Paid' or 'Not Paid', and it should not be blank";
    public static final String VALIDATION_REGEX = "(?i)(paid|not paid)";
    public final String value;

    /**
     * Creates an undefined payment status for the student.
     */
    public Payment() {
        value = "-";
    }

    /**
     * Creates a payment status for a student.
     *
     * @param status Payment status ('Paid' or 'Not Paid').
     */
    public Payment(String status) {
        requireNonNull(status);
        checkArgument(isValidPayment(status), MESSAGE_CONSTRAINTS);
        value = status;
    }

    /**
     * Returns true if a given string is a valid payment status.
     */
    public static boolean isValidPayment(String test) {
        return test.matches(VALIDATION_REGEX);
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
        if (!(other instanceof Payment)) {
            return false;
        }

        Payment otherPayment = (Payment) other;
        return value.equalsIgnoreCase(otherPayment.value);
    }
}
