package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.policy.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    private final String policyName;
    private final String policyID;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName, policyID}.
     */
    @JsonCreator
    public JsonAdaptedPolicy(String policyDetails) {
        String[] args = policyDetails.split(",");
        this.policyName = args[0];
        this.policyID = args[1];
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.policyName;
        policyID = source.policyID;
    }

    @JsonValue
    public String getPolicyDetails() {
        return policyName + "," + policyID;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policy.
     */
    public Policy toModelType() throws IllegalValueException {
        if (!Policy.isValidName(policyName)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS_NAME);
        }
        if (!Policy.isValidID(policyID)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS_ID);
        }
        return new Policy(policyName, policyID);
    }

}
