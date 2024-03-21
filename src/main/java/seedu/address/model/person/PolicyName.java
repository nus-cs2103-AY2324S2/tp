package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's policyName in the address book.
 * Guarantees: immutable; is always valid
 */
public class PolicyName {
    public final String value;

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
