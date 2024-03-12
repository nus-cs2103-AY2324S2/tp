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
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String firstName;
    private final String lastName;
    private final String phone;
    private final String sex;
    private final String employmentType;
    private final String address;
    private final String bankDetails;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("firstName") String firstName,
                             @JsonProperty("lastName") String lastName,
                             @JsonProperty("phone") String phone,
                             @JsonProperty("sex") String sex,
                             @JsonProperty("employmentType") String employmentType,
                             @JsonProperty("address") String address,
                             @JsonProperty("bankDetails") String bankDetails,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.sex = sex;
        this.employmentType = employmentType;
        this.address = address;
        this.bankDetails = bankDetails;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        firstName = source.getFirstName().value;
        lastName = source.getLastName().value;
        phone = source.getPhone().value;
        sex = source.getSex().value;
        employmentType = source.getEmploymentType().value;
        address = source.getAddress().value;
        bankDetails = source.getBankDetails().value;
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

        if (firstName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(firstName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelFirstName = new Name(firstName);

        if (lastName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(lastName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelLastName = new Name(lastName);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (sex == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName()));
        }
        if (!Sex.isValidSex(sex)) {
            throw new IllegalValueException(Sex.MESSAGE_CONSTRAINTS);
        }
        final Sex modelSex = new Sex(sex);

        if (employmentType == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, EmploymentType.class.getSimpleName()));
        }
        if (!EmploymentType.isValidEmploymentType(employmentType)) {
            throw new IllegalValueException(EmploymentType.MESSAGE_CONSTRAINTS);
        }
        final EmploymentType modelEmploymentType = new EmploymentType(employmentType);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (bankDetails == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, BankDetails.class.getSimpleName()));
        }
        if (!BankDetails.isValidBankAccount(bankDetails)) {
            throw new IllegalValueException(BankDetails.MESSAGE_CONSTRAINTS);
        }
        final BankDetails modelBankDetails = new BankDetails(bankDetails);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelFirstName, modelLastName, modelPhone, modelSex, modelEmploymentType,
            modelAddress,
            modelBankDetails, modelTags);
    }

}
