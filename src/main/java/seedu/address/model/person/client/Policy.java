package seedu.address.model.person.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Policy {

    public static final String MESSAGE_CONSTRAINTS =
            "Policy can take on any values, and it should not be blank";

    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    public Policy(String policy) {
        requireNonNull(policy);
        checkArgument(isValidPolicy(policy), MESSAGE_CONSTRAINTS);
        this.value = policy;
    }

    public static boolean isValidPolicy(String test) {
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
        if (!(other instanceof Policy)) {
            return false;
        }

        Policy otherPolicy = (Policy) other;
        return value.equals(otherPolicy.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
