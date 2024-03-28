package scrolls.elder.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import scrolls.elder.commons.exceptions.IllegalValueException;
import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.PersonFactory;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Role;
import scrolls.elder.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String role;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final String pairedWithName;
    private final String pairedWithId;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("address") String address,
            @JsonProperty("role") String role,
            @JsonProperty("tags") List<JsonAdaptedTag> tags,
            @JsonProperty("pairedWithName") String pairedWithName,
            @JsonProperty("pairedWithId") String pairedWithId) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role;
        this.pairedWithName = pairedWithName;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.pairedWithId = pairedWithId;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        id = String.valueOf(source.getPersonId());
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        role = source.getRole().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        pairedWithName = source.getPairedWithName().map(p -> p.fullName).orElse(null);
        pairedWithId = source.getPairedWithId().map(Object::toString).orElse(null);
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

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, int.class.getSimpleName()));
        }
        final int modelId = Integer.parseInt(id);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Optional<Name> modelPairedWithName;
        modelPairedWithName = Optional.ofNullable(pairedWithName).map(Name::new);

        final Optional<Integer> modelPairedWithID;
        modelPairedWithID = Optional.ofNullable(pairedWithId).map(Integer::parseInt);

        Role modelRole;
        if (role == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName()));
        } else if (!Role.isValidRole(role)) {
            throw new IllegalValueException(Role.MESSAGE_CONSTRAINTS);
        } else {
            modelRole = new Role(role);
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        return PersonFactory.withIdFromParams(modelId, modelName, modelPhone, modelEmail, modelAddress, modelRole,
                modelTags, modelPairedWithName, modelPairedWithID);
    }

}
