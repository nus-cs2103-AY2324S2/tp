package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String name;
    private final String phone;
    //TODO: add final, resolve the "may have not been initialized" error
    private final String nric;
    private final String sex;
    private final String status;
    private final String address;
    private final String dateOfBirth;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("nric") String nric, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("dateOfBirth") String dob,
                             @JsonProperty("sex") String sex, @JsonProperty("address") String address,
                             @JsonProperty("status") String status) {
        this.nric = nric;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dob;
        this.sex = sex;
        this.status = status;
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
        this.sex = source.getEmail().toString();
        this.status = source.getStatus().toString();

    }

    /**
     * Converts this Json-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName()));
        }
        if (!DateOfBirth.isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final DateOfBirth modelDateOfBirth = new DateOfBirth(dateOfBirth);
        // Sex Check
        if (sex == null || sex.isBlank()) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName()));
        }
        Sex modelSex;
        if (sex.equals("F")) {
            modelSex = new Sex(Sex.SexType.F);
        } else if (sex.equals("M")) {
            modelSex = new Sex(Sex.SexType.M);
        } else {
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }
        // Status Check
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        Status modelStatus;
        switch (status) {
        case "PENDING":
            modelStatus = new Status(Status.StatusType.PENDING);
            break;
        case "HEALTHY":
            modelStatus = new Status(Status.StatusType.HEALTHY);
            break;
        case "UNWELL":
            modelStatus = new Status(Status.StatusType.UNWELL);
            break;
        default:
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Person(modelNric, modelName, modelPhone, modelAddress, modelDateOfBirth, modelSex, modelStatus);
    }

}
