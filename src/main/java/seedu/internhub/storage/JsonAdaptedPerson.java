package seedu.internhub.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.internhub.commons.exceptions.IllegalValueException;
import seedu.internhub.model.person.Address;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.InternDuration;
import seedu.internhub.model.person.InterviewDate;
import seedu.internhub.model.person.JobDescription;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Note;
import seedu.internhub.model.person.Person;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Salary;
import seedu.internhub.model.person.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
public class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String companyName;
    private final String phone;
    private final String email;
    private final String address;
    private String tag;
    private final String jobDescription;
    private final String interviewDate;
    private final String internDuration;
    private final String salary;
    private final String note;


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("companyName") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tag") String tag, @JsonProperty("jobDescription") String jobDescription,
            @JsonProperty("interviewDate") String interviewDate,
            @JsonProperty("internDuration") String internDuration,
            @JsonProperty("salary") String salary, @JsonProperty("note") String note) {
        this.companyName = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tag = tag;
        this.jobDescription = jobDescription;
        this.interviewDate = interviewDate;
        this.internDuration = internDuration;
        this.salary = salary;
        this.note = note;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        companyName = source.getCompanyName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tag = source.getTag().getTagShort();
        jobDescription = source.getJobDescription().value;
        interviewDate = source.getInterviewDate().toString();
        internDuration = source.getInternDuration().value;
        salary = source.getSalary().value;
        note = source.getNote().value;
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
        final Address modelAddress = new Address(address);

        if (tag == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName()));
        }
        if (!Tag.isValidTag(tag)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        Tag modelTag = new Tag(tag);

        if (jobDescription == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, JobDescription.class.getSimpleName()));
        }
        if (!JobDescription.isValidJobDescription(jobDescription)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        final JobDescription modelJobDescription = new JobDescription(jobDescription);

        final InterviewDate modelInterviewDate;
        if (interviewDate.isEmpty()) {
            modelInterviewDate = new InterviewDate(null);
        } else {
            modelInterviewDate = new InterviewDate(interviewDate);
        }

        if (internDuration == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, InternDuration.class.getSimpleName()));
        }
        if (!InternDuration.isValidInternDuration(internDuration)) {
            throw new IllegalValueException(InternDuration.MESSAGE_CONSTRAINTS);
        }
        final InternDuration modelInternDuration = new InternDuration(internDuration);

        if (salary == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName()));
        }
        if (!Salary.isValidSalary(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);
        final Note modelNote = new Note(note);

        return new Person(modelCompanyName, modelPhone, modelEmail, modelAddress, modelTag,
                modelJobDescription, modelInterviewDate, modelInternDuration, modelSalary, modelNote);
    }

}
