package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.techstack.TechStack;


/**
 * Jackson-friendly version of {@link TechStack}.
 */
class JsonAdaptedTechStack {

    private final String techStackName;

    /**
     * Constructs a {@code JsonAdaptedTechStack} with the given {@code techStackName}.
     */
    @JsonCreator
    public JsonAdaptedTechStack(String techStackName) {
        this.techStackName = techStackName;
    }

    /**
     * Converts a given {@code TechStack} into this class for Jackson use.
     */
    public JsonAdaptedTechStack(TechStack source) {
        techStackName = source.techStackName;
    }

    @JsonValue
    public String getTechStackName() {
        return techStackName;
    }

    /**
     * Converts this Jackson-friendly adapted tech stack object into the model's {@code TechStack} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tech stack.
     */
    public TechStack toModelType() throws IllegalValueException {
        if (!TechStack.isValidTechStackName(techStackName)) {
            throw new IllegalValueException(TechStack.MESSAGE_CONSTRAINTS);
        }
        return new TechStack(techStackName);
    }

}
