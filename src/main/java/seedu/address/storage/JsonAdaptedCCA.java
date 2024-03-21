package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cca.Cca;

/**
 * Jackson-friendly version of {@link CCA}.
 */
class JsonAdaptedCca {

    private final String ccaName;

    /**
     * Constructs a {@code JsonAdaptedCCA} with the given {@code CCAName}.
     */
    @JsonCreator
    public JsonAdaptedCca(String ccaName) {
        this.ccaName = ccaName;
    }

    /**
     * Converts a given {@code CCA} into this class for Jackson use.
     */
    public JsonAdaptedCca(Cca source) {
        ccaName = source.ccaName;
    }

    @JsonValue
    public String getCcaName() {
        return ccaName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code CCA} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted CCA.
     */
    public Cca toModelType() throws IllegalValueException {
        if (!Cca.isValidCcaName(ccaName)) {
            throw new IllegalValueException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(ccaName);
    }

}
