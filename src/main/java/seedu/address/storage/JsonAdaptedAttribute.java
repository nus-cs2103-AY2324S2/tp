package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.attribute.StringAttribute;

/**
 * Jackson-friendly version of {@link Attribute}.
 */
class JsonAdaptedAttribute {

    public final String value;
    private final String name;

    /**
     * Constructs a {@code JsonAdaptedAttribute} with the given attribute details.
     */
    @JsonCreator
    public JsonAdaptedAttribute(@JsonProperty("name") String name, @JsonProperty("value") String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Converts a given {@code Attribute} into this class for Jackson use.
     */
    public JsonAdaptedAttribute(Attribute source) {
        this.name = source.getName();
        this.value = source.getValueAsString();
    }

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attribute.
     */
    public Attribute toModelType() throws IllegalValueException {
        if (name == null || value == null || name.isEmpty() || value.isEmpty()) {
            throw new IllegalValueException("Invalid attribute type or value in JSON.");
        }
        if (name.equals("Name") || name.equals("name")) {
            return new NameAttribute("Name", value);
        } else {
            return new StringAttribute(name, value);
        }
    }
}
