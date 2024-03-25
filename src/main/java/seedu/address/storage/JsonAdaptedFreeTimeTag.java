package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.FreeTimeTag;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedFreeTimeTag {

    private final String freeTimeTagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedFreeTimeTag(String freeTimeTagName) {
        this.freeTimeTagName = freeTimeTagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedFreeTimeTag(FreeTimeTag source) {
        freeTimeTagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return freeTimeTagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public FreeTimeTag toModelType() throws IllegalValueException {
        if (!FreeTimeTag.isValidTagName(freeTimeTagName)) {
            throw new IllegalValueException(FreeTimeTag.MESSAGE_CONSTRAINTS);
        }
        return new FreeTimeTag(freeTimeTagName);
    }

}
