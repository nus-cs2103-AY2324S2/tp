package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    private final String policyName;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName}.
     */
    @JsonCreator
    public JsonAdaptedPolicy(String policyName) {
        this.policyName = policyName;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.value;
    }


    @JsonValue
    public String getPolicyName() {
        return policyName;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policies.
     */
    public Policy toModelType() throws IllegalValueException {
        return new Policy(policyName);
    }

}
