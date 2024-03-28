package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.allergen.Allergen;

/**
 * Jackson-friendly version of {@link Allergen}.
 */
class JsonAdaptedAllergen {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAllergen(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAllergen(Allergen source) {
        tagName = source.allergenName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Allergen toModelType() throws IllegalValueException {
        if (!Allergen.isValidAllergenName(tagName)) {
            throw new IllegalValueException(Allergen.MESSAGE_CONSTRAINTS);
        }
        return new Allergen(tagName);
    }

}
