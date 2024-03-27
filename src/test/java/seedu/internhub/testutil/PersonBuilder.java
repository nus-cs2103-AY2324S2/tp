package seedu.internhub.testutil;

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
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "";
    public static final String DEFAULT_TAG = "NR";
    public static final String DEFAULT_JOB_DESCRIPTION = "Software Developer Intern";
    public static final String DEFAULT_INTERVIEW_DATE = null;
    public static final String DEFAULT_INTERN_DURATION = "6 months";
    public static final String DEFAULT_SALARY = "500";
    public static final String DEFAULT_NOTE = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Tag tag;
    private JobDescription jobDescription;
    private InterviewDate interviewDate;
    private InternDuration internDuration;
    private Salary salary;
    private Note note;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tag = new Tag(DEFAULT_TAG);
        jobDescription = new JobDescription(DEFAULT_JOB_DESCRIPTION);
        interviewDate = new InterviewDate(DEFAULT_INTERVIEW_DATE);;
        internDuration = new InternDuration(DEFAULT_INTERN_DURATION);;
        salary = new Salary(DEFAULT_SALARY);
        note = new Note(DEFAULT_NOTE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getCompanyName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tag = personToCopy.getTag();
        jobDescription = personToCopy.getJobDescription();
        interviewDate = personToCopy.getInterviewDate();
        internDuration = personToCopy.getInternDuration();
        salary = personToCopy.getSalary();
        note = personToCopy.getNote();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String tag) {
        this.tag = new Tag(tag);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code jobDescription} of the {@code Person} that we are building.
     */
    public PersonBuilder withJobDescription(String jobDescription) {
        this.jobDescription = new JobDescription(jobDescription);
        return this;
    }

    /**
     * Sets the {@code interviewDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withInterviewDate(String interviewDate) {
        this.interviewDate = new InterviewDate(interviewDate);
        return this;
    }

    /**
     * Sets the {@code internDuration} of the {@code Person} that we are building.
     */
    public PersonBuilder withInternDuration(String internDuration) {
        this.internDuration = new InternDuration(internDuration);
        return this;
    }

    /**
     * Sets the {@code salary} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }
    /**
     * Sets the {@code note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Build the person
     */
    public Person build() {
        return new Person(name, phone, email, address, tag,
                jobDescription, interviewDate, internDuration, salary, note);
    }

}
