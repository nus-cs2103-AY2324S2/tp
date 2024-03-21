package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a policy associated with a person.
 * Guarantees: immutable; policyName is valid as declared in {@link #isValidField(String)}
 */
public class Policy {

    public static final String STRING_VALIDATION_REGEX = "[^\\s].*";
    public static final String NUMBER_VALIDATION_REGEX = "\\d{3,}";

    public static final String MESSAGE_CONSTRAINTS = "Policy Name must be alphanumeric";

    public final String policyName;

    /**
     * Constructs a {@code Policy}.
     *
     * @param policyName A valid policy name.
     */
    public Policy(String policyName) {
        requireAllNonNull(policyName);
        checkArgument(isValidField(policyName), MESSAGE_CONSTRAINTS);
        this.policyName = policyName;
    }

    /**
     * Returns true if a given string is a valid policy name.
     *
     * @param field The policy name to validate.
     * @return True if the policy name is valid, false otherwise.
     */
    public static boolean isValidField(String field) {
        return field.matches(STRING_VALIDATION_REGEX);
    }

    public static boolean isValidNumber(String field) {
        return field.matches(NUMBER_VALIDATION_REGEX);
    }

}
