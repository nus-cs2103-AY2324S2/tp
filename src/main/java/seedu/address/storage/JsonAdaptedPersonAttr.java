package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPersonAttr {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final List<JsonAdaptedAttribute> attributes = new ArrayList<>();

    private final String uuid;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPersonAttr(@JsonProperty("uuid") String uuid,
                                 @JsonProperty("attributes") List<JsonAdaptedAttribute> attributes) {
        if (attributes != null) {
            this.attributes.addAll(attributes);;
        }
        this.uuid = uuid;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPersonAttr(Person source) {
        attributes.addAll(source.getAttributes().stream()
                .map(JsonAdaptedAttribute::new)
                .collect(Collectors.toList()));
        this.uuid = source.getUuidString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Attribute> personAttributes = new ArrayList<>();
        for (JsonAdaptedAttribute attribute : attributes) {
            personAttributes.add(attribute.toModelType());
        }

        if (personAttributes.isEmpty()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Attributes"));
        }

        return new Person(UUID.fromString(uuid), personAttributes.toArray(new Attribute[0]));
    }

    public List<JsonAdaptedAttribute> getAttributes() {
        return attributes;
    }
}
