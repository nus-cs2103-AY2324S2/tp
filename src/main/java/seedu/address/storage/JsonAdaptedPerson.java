package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import java.util.logging.Logger;


/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    private static final Logger logger = LogsCenter.getLogger(JsonAdaptedPerson.class);

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String meeting;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedPolicyTag> policies = new ArrayList<>();


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("meeting") String meeting, @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("policies") List<JsonAdaptedPolicyTag> policies) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.meeting = meeting;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (policies != null) {
            this.policies.addAll(policies);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        this.name = source.getName().fullName;
        this.phone = source.getPhone().value;
        this.email = source.getEmail().value;
        this.address = source.getAddress().value;
        this.meeting = source.getMeeting().value;

        this.tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        this.policies.addAll(source.getPolicies().stream()
                .peek(policy -> logger.info(policy.toString()))
                .map(JsonAdaptedPolicyTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        final List<Policy> personPolicies = new ArrayList<>();

        for (JsonAdaptedPolicyTag policy : policies) {
            personPolicies.add(policy.toModelType());
        }

        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

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

        if (meeting == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Meeting.class.getSimpleName()));
        }
        if (!Meeting.isValidMeeting(meeting)) {
            throw new IllegalValueException(Meeting.MESSAGE_CONSTRAINTS);
        }
        final Meeting modelMeeting = new Meeting(meeting);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<Policy> modelPolicies = new HashSet<>(personPolicies);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelMeeting, modelTags, modelPolicies);
    }

}
