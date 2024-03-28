package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String parentPhoneNumberOne;
    private final String parentPhoneNumberTwo;
    private final String email;
    private final String address;
    private final String studentId;
    private final String formClass;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("parent phone one") String parentPhoneNumberOne,
                             @JsonProperty("parent phone two") String parentPhoneNumberTwo,
                             @JsonProperty("email") String email,
                             @JsonProperty("address") String address,
                             @JsonProperty("studentId") String studentId,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("formClass") String formClass) {
        this.name = name;
        this.parentPhoneNumberOne = parentPhoneNumberOne;
        this.parentPhoneNumberTwo = parentPhoneNumberTwo;
        this.email = email;
        this.address = address;
        this.studentId = studentId;
        this.formClass = formClass;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        parentPhoneNumberOne = source.getParentPhoneOne().value;
        parentPhoneNumberTwo = source.getParentPhoneTwo().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        studentId = source.getStudentId().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        formClass = source.getFormClass().value;
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (parentPhoneNumberOne == null || parentPhoneNumberTwo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(parentPhoneNumberOne) || !Phone.isValidPhone(parentPhoneNumberTwo)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelFirstParentPhone = new Phone(parentPhoneNumberOne);
        final Phone modelSecondParentPhone = new Phone(parentPhoneNumberTwo);

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

        if (studentId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    StudentId.class.getSimpleName()));
        }
        if (!StudentId.isValidStudentId(studentId)) {
            throw new IllegalValueException(StudentId.MESSAGE_CONSTRAINTS);
        }
        final StudentId modelStudentId = new StudentId(studentId);

        if (formClass == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    FormClass.class.getSimpleName()));
        }
        if (!FormClass.isValidClassName(formClass)) {
            throw new IllegalValueException(FormClass.MESSAGE_CONSTRAINTS);
        }
        final FormClass modelFormClass = new FormClass(formClass);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName, modelFirstParentPhone, modelSecondParentPhone, modelEmail, modelAddress,
                modelStudentId, modelTags, modelFormClass);
    }

}
