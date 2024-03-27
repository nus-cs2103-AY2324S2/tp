package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's policyName in the address book.
 * Guarantees: immutable; is always valid
 */
public class PolicyName {

    public static final String MESSAGE_CONSTRAINTS = "Policy Name can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs a {@code PolicyName}.
     *
     * @param policyName A valid policy name.
     */
    public PolicyName(String policyName) {
        requireNonNull(policyName);
        value = policyName;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PolicyName // instanceof handles nulls
                && value.equals(((PolicyName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
