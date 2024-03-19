package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the income of a person in the address book.
 * Guarantees: income is present, not null, and adheres to specific constraints.
 */
public class Income {

    /** Message for constraints on income. */
    public static final String MESSAGE_CONSTRAINTS = "Income should be an integer and should be at least 0";
    public static final String VALIDATION_REGEX = "^[0-9]+$";

    /** The income value. */
    private final String incomeValue;

    /**
     * Constructs an {@code Income} instance with the given income value.
     *
     * @param incomeValue The income value.
     */
    public Income(String incomeValue) {
        requireNonNull(incomeValue);
        checkArgument(isValidIncome(incomeValue), MESSAGE_CONSTRAINTS);
        this.incomeValue = incomeValue;
    }

    /**
     * Checks if the given income value is a valid value.
     *
     * @param incomeValue The income value to check.
     * @return True if the income value is greater than or equal to zero, false otherwise.
     */
    public static boolean isValidIncome(String incomeValue) {
        return incomeValue.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the string representation of the income value.
     *
     * @return The string representation of the income value.
     */
    @Override
    public String toString() {
        return incomeValue.toString();
    }

    /**
     * Returns a string representation of the income value with additional descriptive text.
     *
     * @return A string representation with descriptive text.
     */
    public String toStringWithRepresentation() {
        return "Income is $" + incomeValue;
    }

    /**
     * Checks if this {@code Income} instance is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true; // short circuit if same object
        }
        if (!(other instanceof Income)) {
            return false; // instanceof handles nulls
        }
        Income otherIncome = (Income) other;
        return incomeValue.equals(otherIncome.incomeValue); // state check
    }
}
