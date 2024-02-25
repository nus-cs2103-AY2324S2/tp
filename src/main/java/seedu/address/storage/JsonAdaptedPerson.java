package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InternDuration;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobDescription;
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
    private final String phone;
    private final String email;
    private final String address;
    private final String tag;
    private final String jobDescription;
    private final String interviewDate;
    private final String internDuration;
    private final String salary;


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("companyName") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tag") String tag, @JsonProperty("jobDescription") String jobDescription,
            @JsonProperty("interviewDate") String interviewDate,
            @JsonProperty("internDuration") String internDuration,
            @JsonProperty("salary") String salary) {
        this.companyName = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tag = tag;
        this.jobDescription = jobDescription;
        this.interviewDate = interviewDate;
        this.internDuration = internDuration;
        this.salary = salary;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        companyName = source.getCompanyName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tag = source.getTag().tagName;
        jobDescription = source.getJobDescription().value;
        interviewDate = source.getInterviewDate().toString();
        internDuration = source.getInternDuration().value;
        salary = source.getSalary().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

        if (companyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(companyName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelCompanyName = new Name(companyName);

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

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }
        if (!Tag.isValidTagName(tag)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Tag modelTag = new Tag(tag);

        if (jobDescription == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, JobDescription.class.getSimpleName()));
        }
        if (!JobDescription.isValidJobDescription(jobDescription)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        final JobDescription modelJobDescription = new JobDescription(jobDescription);

        if (interviewDate == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewDate.class.getSimpleName()));
        }
        if (!InterviewDate.isValidInterviewDate(interviewDate)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }

        final InterviewDate modelInterviewDate = new InterviewDate(interviewDate);


        if (internDuration == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InternDuration.class.getSimpleName()));
        }
        if (!InternDuration.isValidInternDuration(jobDescription)) {
            throw new IllegalValueException(InternDuration.MESSAGE_CONSTRAINTS);
        }
        final InternDuration modelInternDuration = new InternDuration(internDuration);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(InternDuration.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        return new Person(modelCompanyName, modelPhone, modelEmail, modelAddress, modelTag,
                modelJobDescription, modelInterviewDate, modelInternDuration, modelSalary);
    }

}
