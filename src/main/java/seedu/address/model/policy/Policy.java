package seedu.address.model.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * The type Policy.
 */
public class Policy {
    /**
     * The constant MESSAGE_CONSTRAINTS_NAME.
     */
    public static final String MESSAGE_CONSTRAINTS_NAME =
            "Policy names should only contain alphanumeric characters and spaces, and it should not be blank";
    /**
     * The constant MESSAGE_CONSTRAINTS_ID.
     */
    public static final String MESSAGE_CONSTRAINTS_ID =
            "Policy ID should only contain numbers, and it should be at least 1 digits long";
    /**
     * The constant VALIDATION_NAME_REGEX.
     */
    public static final String VALIDATION_NAME_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    /**
     * The constant VALIDATION_ID_REGEX.
     */
    public static final String VALIDATION_ID_REGEX = "\\d+";
    /**
     * The Policy name.
     */
    public final String policyName;
    /**
     * The Policy id.
     */
    public final String policyID;
    private final PolicyType type;


    /**
     * Instantiates a new Policy.
     *
     * @param policyName the policy name
     * @param policyID   the policy id
     */
    public Policy(String policyName, String policyID) {
        requireNonNull(policyName, policyID);
        checkArgument(isValidName(policyName), MESSAGE_CONSTRAINTS_NAME);
        checkArgument(isValidID(policyID), MESSAGE_CONSTRAINTS_ID);
        this.policyName = policyName;
        this.policyID = policyID;
        this.type = PolicyType.DEFAULT;
    }

    /**
     * Is id boolean.
     *
     * @param testID the test id
     * @return the boolean
     */
    public boolean isID(String testID) {
        return testID.equals(policyID);
    }

    /**
     * Is valid name boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_NAME_REGEX);
    }

    /**
     * Is valid id boolean.
     *
     * @param test the test
     * @return the boolean
     */
    public static boolean isValidID(String test) {
        return test.matches(VALIDATION_ID_REGEX);
    }

    /**
     * Has same id boolean.
     *
     * @param policy the policy
     * @return the boolean
     */
    public boolean hasSameID(Policy policy) {
        return policyID.equals(policy.policyID);
    }
    @Override
    public String toString() {
        return "Name:" + policyName + ", Type:" + type + ", Policy ID:" + policyID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Policy)) {
            return false;
        }

        Policy policy = (Policy) obj;
        return policyID.equals(policy.policyID) && policyName.equals(policy.policyName);
    }
}
