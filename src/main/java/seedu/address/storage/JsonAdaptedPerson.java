package seedu.address.storage;

import java.util.ArrayList;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Notes;
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
    private final ArrayList<String> notes;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("classGroup") String classGroup,
                             @JsonProperty("telegram") String telegram, @JsonProperty("github") String github,
                             @JsonProperty("notes") ArrayList<String> notes) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.classGroup = classGroup;
        this.telegram = telegram;
        this.github = github;
        this.notes = notes;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().orElse(Phone.EMPTY).value;
        email = source.getEmail().value;
        classGroup = source.getClassGroup().classGroup;
        telegram = source.getTelegram().orElse(Telegram.EMPTY).telegramId;
        github = source.getGithub().orElse(Github.EMPTY).githubId;
        notes = source.getNotes().getAsStrings();
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

        final Optional<Phone> modelPhone;
        if (phone == null || phone.isEmpty()) {
            modelPhone = Optional.of(Phone.EMPTY);
        } else if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        } else {
            modelPhone = Optional.of(new Phone(phone));
        }

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

        final Optional<Telegram> modelTelegram;
        if (telegram == null || telegram.isEmpty()) {
            modelTelegram = Optional.of(Telegram.EMPTY);
        } else if (!Telegram.isValidTelegram(telegram)) {
            throw new IllegalValueException(Telegram.MESSAGE_CONSTRAINTS);
        } else {
            modelTelegram = Optional.of(new Telegram(telegram));
        }

        final Optional<Github> modelGithub;
        if (github == null || github.isEmpty()) {
            modelGithub = Optional.of(Github.EMPTY);
        } else if (!Github.isValidGithub(github)) {
            throw new IllegalValueException(Github.MESSAGE_CONSTRAINTS);
        } else {
            modelGithub = Optional.of(new Github(github));
        }

        final Notes modelNotes = new Notes();
        if (notes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Notes.class.getSimpleName()));
        }
        for (String note: notes) {
            if (!Note.isValidNote(note)) {
                throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
            } else {
                Note modelNote = new Note(note);
                modelNotes.addNote(modelNote);
            }
        }

        return new Person(modelName, modelClassGroup, modelEmail, modelPhone, modelTelegram, modelGithub, modelNotes);
    }
}
