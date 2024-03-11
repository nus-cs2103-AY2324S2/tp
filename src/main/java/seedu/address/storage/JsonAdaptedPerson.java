package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.Entry;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getEntry("Name").getDescription();
        phone = source.getEntry("Phone").getDescription();
        email = source.getEntry("Email").getDescription();
        address = source.getEntry("Address").getDescription();
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Entry.class.getSimpleName()));
        }
        if (!Entry.isValid("Name", name)) {
            throw new IllegalValueException(Entry.MESSAGE_CONSTRAINTS);
        }
        final Entry modelName = new Entry("Name", name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Entry.class.getSimpleName()));
        }
        if (!Entry.isValid("Phone", phone)) {
            throw new IllegalValueException(Entry.MESSAGE_CONSTRAINTS);
        }
        final Entry modelPhone = new Entry("Phone", phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Entry.class.getSimpleName()));
        }
        if (!Entry.isValid("Email", email)) {
            throw new IllegalValueException(Entry.MESSAGE_CONSTRAINTS);
        }
        final Entry modelEmail = new Entry("Email", email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Entry.class.getSimpleName()));
        }
        if (!Entry.isValid("Address", address)) {
            throw new IllegalValueException(Entry.MESSAGE_CONSTRAINTS);
        }
        final Entry modelAddress = new Entry("Address", address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        Person result = new Person(modelName, modelTags);
        result.addEntry(modelPhone);
        result.addEntry(modelEmail);
        result.addEntry(modelAddress);
        return result;
    }

}
