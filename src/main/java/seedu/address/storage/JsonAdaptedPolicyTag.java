package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
public class JsonAdaptedPolicyTag {
    public final String policyName;
    public final String policyNumber;
    public final String premiumTerm;
    public final String premium;
    public final String benefit;


    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedPolicyTag(@JsonProperty("policyName") String policyName,
                                @JsonProperty("policyNumber") String policyNumber,
                                @JsonProperty("premiumTerm") String premiumTerm,
                                @JsonProperty("premium") String premium,
                                @JsonProperty("benefit") String benefit) {
        this.policyName = policyName;
        this.policyNumber = policyNumber;
        this.premiumTerm = premiumTerm;
        this.premium = premium;
        this.benefit = benefit;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedPolicyTag(Policy source) {
        policyName = source.policyName;
        policyNumber = source.policyNumber;
        premiumTerm = source.premiumTerm;
        premium = source.premium;
        benefit = source.benefit;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Policy toModelType() throws IllegalValueException {
        if (!Policy.isValidField(policyName)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidNumber(policyNumber)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidField(premiumTerm)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidNumber(premium)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS);
        }
        if (!Policy.isValidNumber(benefit)) {
            throw new IllegalValueException(Policy.MESSAGE_CONSTRAINTS);
        }
        return new Policy(policyName, policyNumber, premiumTerm, premium, benefit);
    }
}
