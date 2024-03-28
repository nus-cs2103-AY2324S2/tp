package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.person.Address;
import seedu.address.model.person.CompanyName;
import seedu.address.model.person.Email;
import seedu.address.model.person.Info;
import seedu.address.model.person.InterviewTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    private final String companyName;

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String dateTime;
    private final String salary;
    private final String info;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedProgrammingLanguage> programmingLanguages = new ArrayList<>();
    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("company name") String companyName, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("address") String address, @JsonProperty("dateTime") String dateTime,
                             @JsonProperty("salary") String salary, @JsonProperty("info") String info,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags,
                             @JsonProperty("programmingLanguages") List<JsonAdaptedProgrammingLanguage>
                                         programmingLanguages,
                             @JsonProperty("priority") String priority) {
        this.companyName = companyName;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dateTime = dateTime;
        this.salary = salary;
        this.info = info;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        if (programmingLanguages != null) {
            this.programmingLanguages.addAll(programmingLanguages);
        }
        this.priority = priority;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        companyName = source.getCompanyName().companyName;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        dateTime = source.getDateTime().rawToString();
        salary = source.getSalary().toString();
        info = source.getInfo().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        programmingLanguages.addAll(source.getProgrammingLanguages().stream()
                .map(JsonAdaptedProgrammingLanguage::new)
                .collect(Collectors.toList()));
        priority = Integer.toString(source.getPriority());
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
        final List<ProgrammingLanguage> personProgrammingLanguages = new ArrayList<>();
        for (JsonAdaptedProgrammingLanguage language : programmingLanguages) {
            personProgrammingLanguages.add(language.toModelType());
        }
        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(companyName)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelCompanyName = new CompanyName(companyName);

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
        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    InterviewTime.class.getSimpleName()));
        }
        if (!InterviewTime.isValidInterviewTime(dateTime)) {
            throw new IllegalValueException(InterviewTime.MESSAGE_CONSTRAINTS);
        }
        final InterviewTime modelDateTime = new InterviewTime(dateTime);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }

        final Salary modelSalary = new Salary(salary);

        final Info modelInfo = new Info(info);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final Set<ProgrammingLanguage> modelProgrammingLanguages = new HashSet<>(personProgrammingLanguages);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Priority"));
        }

        if (!Person.isValidPriority(priority)) {
            throw new IllegalValueException("Priority level should be between 1 and 3");
        }

        final int modelPriority = Integer.parseInt(priority);

        return new Person(modelCompanyName, modelName, modelPhone, modelEmail, modelAddress, modelDateTime,
                modelSalary, modelInfo, modelTags, modelProgrammingLanguages, modelPriority);
    }
}
