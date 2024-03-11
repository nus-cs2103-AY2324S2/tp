package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String classGroup;
    private final String telegram;
    private final String github;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("classGroup") String classGroup,
            @JsonProperty("telegram") String telegram, @JsonProperty("github") String github) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.classGroup = classGroup;
        this.telegram = telegram;
        this.github = github;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        classGroup = source.getClassGroup().classGroup;
        telegram = source.getTelegram().isPresent() ? source.getTelegram().get().telegramId : "";
        github = source.getGithub().isPresent() ? source.getGithub().get().githubId : "";
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

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

        if (classGroup == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, ClassGroup.class.getSimpleName()));
        }
        if (!ClassGroup.isValidClassGroup(classGroup)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final ClassGroup modelClassGroup = new ClassGroup(classGroup);

        final Telegram modelTelegram;
        if (telegram == null) {
            modelTelegram = null;
        } else if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        } else {
            modelTelegram = new Telegram(telegram);
        }

        final Github modelGithub;
        if (github == null) {
            modelGithub = null;
        } else if (!Github.isValidGithub(github)) {
            throw new IllegalValueException(Github.MESSAGE_CONSTRAINTS);
        } else {
            modelGithub = new Github(github);
        }

        return new Person(modelName, modelClassGroup, modelEmail, modelPhone, modelTelegram, modelGithub);
    }
}
