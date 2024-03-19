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
    public final String policyId;
    private final PolicyType policyType;


    /**
     * Instantiates a new Policy.
     *
     * @param policyName the policy name
     * @param policyId   the policy id
     */
    public Policy(String policyName, String policyId) {
        requireNonNull(policyName, policyId);
        checkArgument(isValidName(policyName), MESSAGE_CONSTRAINTS_NAME);
        checkArgument(isValidId(policyId), MESSAGE_CONSTRAINTS_ID);
        this.policyName = policyName;
        this.policyId = policyId;
        this.policyType = PolicyType.DEFAULT;
    }

    public String getPolicyId() {
        return policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public PolicyType getPolicyType() {
        return policyType;
    }

    /**
     * Is id boolean.
     *
     * @param testID the test id
     * @return the boolean
     */
    public boolean isID(String testID) {
        return testID.equals(policyId);
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
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_ID_REGEX);
    }

    /**
     * Has same id boolean.
     *
     * @param policy the policy
     * @return the boolean
     */
    public boolean hasSameId(Policy policy) {
        return policyId.equals(policy.policyId);
    }
    @Override
    public String toString() {
        return "Name:" + policyName + ", Type:" + policyType + ", Policy ID:" + policyId;
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
        return policyId.equals(policy.policyId) && policyName.equals(policy.policyName);
    }
}
