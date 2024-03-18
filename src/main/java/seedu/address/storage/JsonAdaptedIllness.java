package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.illness.Illness;

/**
 * Jackson-friendly version of {@link Illness}.
 */
class JsonAdaptedIllness {

    private final String illnessName;

    /**
     * Constructs a {@code JsonAdaptedIllness} with the given {@code illnessName}.
     */
    @JsonCreator
    public JsonAdaptedIllness(String illnessName) {
        this.illnessName = illnessName;
    }

    /**
     * Converts a given {@code Illness} into this class for Jackson use.
     */
    public JsonAdaptedIllness(Illness source) {
        illnessName = source.illnessName;
    }

    @JsonValue
    public String getTagName() {
        return illnessName;
    }

    /**
     * Converts this Jackson-friendly adapted illness object into the model's {@code Illness} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted illness.
     */
    public Illness toModelType() throws IllegalValueException {
        if (!Illness.isValidIllnessName(illnessName)) {
            throw new IllegalValueException(Illness.MESSAGE_CONSTRAINTS);
        }
        return new Illness(illnessName);
    }

}
