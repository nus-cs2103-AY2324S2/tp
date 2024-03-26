package seedu.address.storage;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Policy;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    private final String policyName;
    private final LocalDate expiryDate;
    private final double premium;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName}.
     */
    @JsonCreator
    public JsonAdaptedPolicy(@JsonProperty("policyName") String policyName,
                             @JsonProperty("expiryDate") LocalDate expiryDate,
                             @JsonProperty("premium") double premium) {
        this.policyName = policyName;
        this.expiryDate = expiryDate;
        this.premium = premium;
    }


    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policyName = source.value;
        expiryDate = source.expiryDate;
        premium = source.premium;
    }



    @JsonProperty
    public String getPolicyName() {
        return policyName;
    }

    @JsonProperty
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty
    public double getPremium() {
        return premium;
    }

    /**
     * Converts this Jackson-friendly adapted policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted policies.
     */
    public Policy toModelType() throws IllegalValueException {
        return new Policy(policyName, expiryDate, premium);
    }

}
