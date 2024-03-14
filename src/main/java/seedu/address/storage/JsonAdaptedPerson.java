package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.note.Note;
import seedu.address.model.person.illness.Illness;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String nric;
    private final String name;
    private final String gender;
    private final String birthDate;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedIllness> illnesses = new ArrayList<>();
    private final List<JsonAdapatedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("nric") String nric,
                             @JsonProperty("name") String name,
                             @JsonProperty("gender") String gender,
                             @JsonProperty("birthdate") String birthDate,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email,
                             @JsonProperty("tags") List<JsonAdaptedIllness> illnesses,
                             @JsonProperty("notes") List<JsonAdapatedNote> notes) {
        this.nric = nric;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        if (illnesses != null) {
            this.illnesses.addAll(illnesses);
        }
        if (notes != null) {
            this.notes.addAll(notes);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        nric = source.getNric().nric;
        name = source.getName().fullName;
        gender = source.getGender().gender;
        birthDate = source.getBirthDate().birthDate;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        illnesses.addAll(source.getIllnesses().stream()
            .map(JsonAdaptedIllness::new)
            .collect(Collectors.toList()));
        notes.addAll(source.getNotes().stream()
            .map(JsonAdapatedNote::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Illness> illnesses = new ArrayList<>();
        for (JsonAdaptedIllness illness : this.illnesses) {
            illnesses.add(illness.toModelType());
        }

        final ObservableList<Note> notes = FXCollections.observableArrayList();
        for (JsonAdapatedNote note : this.notes) {
            notes.add(note.toModelType());
        }

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);

        if (birthDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName()));
        }
        if (!BirthDate.isValidBirthDate(birthDate)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final BirthDate modelBirthDate = new BirthDate(birthDate);

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

        final Set<Illness> modelIllnesses = new HashSet<>(illnesses);
        return new Person(modelNric, modelName, modelGender, modelBirthDate,
                modelPhone, modelEmail, modelIllnesses, notes);
    }
}
