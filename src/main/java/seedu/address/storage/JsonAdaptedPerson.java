package seedu.address.storage;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Allergies;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Condition;
import seedu.address.model.person.Country;
import seedu.address.model.person.DateOfAdmission;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Diagnosis;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;
import seedu.address.model.person.Symptom;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final String phone;
    private final String nric;
    private final String sex;
    private final String status;
    private final String address;
    private final String dateOfBirth;
    private final Optional<String> email;
    private final Optional<String> country;
    private final Optional<String> allergies;
    private final Optional<String> bloodType;
    private final Optional<String> condition;
    private final Optional<String> dateOfAdmission;
    private final Optional<String> diagnosis;
    private final Optional<String> symptom;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("nric") String nric, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("address") String address,
                             @JsonProperty("dateOfBirth") String dob, @JsonProperty("sex") String sex,
                             @JsonProperty("status") String status, @JsonProperty("email") String email,
                             @JsonProperty("country") String country, @JsonProperty("allergies") String allergies,
                             @JsonProperty("bloodType") String bloodType, @JsonProperty("condition") String condition,
                             @JsonProperty("dateOfAdmission") String doa, @JsonProperty("diagnosis") String diagnosis,
                             @JsonProperty("symptom") String symptom) {
        this.nric = nric;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dob;
        this.sex = sex;
        this.status = status;
        this.email = Optional.ofNullable(email);
        this.country = Optional.ofNullable(country);
        this.allergies = Optional.ofNullable(allergies);
        this.bloodType = Optional.ofNullable(bloodType);
        this.condition = Optional.ofNullable(condition);
        this.dateOfAdmission = Optional.ofNullable(doa);
        this.diagnosis = Optional.ofNullable(diagnosis);
        this.symptom = Optional.ofNullable(symptom);
    }

    /**
     * Converts a given {@code Person} into this class for Json use.
     */
    public JsonAdaptedPerson(Person source) {
        this.nric = source.getNric().toString();
        this.name = source.getName().toString();
        this.phone = source.getPhone().toString();
        this.address = source.getAddress().toString();
        this.dateOfBirth = source.getDateOfBirth().toString();
        this.sex = source.getSex().toString();
        this.status = source.getStatus().toString();
        this.email = Optional.ofNullable(source.getEmail()).map(Email::toString);
        this.country = Optional.ofNullable(source.getCountry()).map(Country::toString);
        this.allergies = Optional.ofNullable(source.getAllergies()).map(Allergies::toString);
        this.bloodType = Optional.ofNullable(source.getBloodType()).map(BloodType::toString);
        this.condition = Optional.ofNullable(source.getCondition()).map(Condition::toString);
        this.dateOfAdmission = Optional.ofNullable(source.getDateOfAdmission()).map(DateOfAdmission::toString);
        this.diagnosis = Optional.ofNullable(source.getDiagnosis()).map(Diagnosis::toString);
        this.symptom = Optional.ofNullable(source.getSymptom()).map(Symptom::toString);
    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

        Person person;

        // NRIC Check
        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Nric.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);
        // Name Check
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);
        // Phone Check
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);
        // Address Check
        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        // Date of Birth Check
        if (dateOfBirth == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalValueException(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        final DateOfBirth modelDateOfBirth = new DateOfBirth(dateOfBirth);
        // Sex Check
        if (sex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName()));
        }
        if (!Sex.isValidSex(sex)) {
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }
        final Sex modelSex = new Sex(sex);
        // Status Check
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        person = new Person(modelNric, modelName, modelPhone, modelAddress, modelDateOfBirth, modelSex, modelStatus);

        // Email check
        if (email.isPresent()) {
            final Email modelEmail = new Email(email.get());
            person.setEmail(modelEmail);
        }
        // Country check
        if (country.isPresent()) {
            final Country modelCountry = new Country(country.get());
            person.setCountry(modelCountry);
        }
        // Allergies check
        if (allergies.isPresent()) {
            final Allergies modelAllergies = new Allergies(allergies.get());
            person.setAllergies(modelAllergies);
        }
        // BloodType check
        if (bloodType.isPresent()) {
            final BloodType modelBloodType = new BloodType(bloodType.get());
            person.setBloodType(modelBloodType);
        }
        //Condition check
        if (condition.isPresent()) {
            final Condition modelCondition = new Condition(condition.get());
            person.setCondition(modelCondition);
        }
        //Date of Admission check
        if (dateOfAdmission.isPresent()) {
            final DateOfAdmission modelDoa = new DateOfAdmission(dateOfAdmission.get());
            person.setDateOfAdmission(modelDoa);
        }
        //Diagnosis check
        if (diagnosis.isPresent()) {
            final Diagnosis modelDiagnosis = new Diagnosis(diagnosis.get());
            person.setDiagnosis(modelDiagnosis);
        }
        //Symptom check
        if (symptom.isPresent()) {
            final Symptom modelSymptom = new Symptom(symptom.get());
            person.setSymptom(modelSymptom);
        }
        return person;
    }

}
