package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Applicant;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Interviewer;
import seedu.address.model.person.InterviewerStatus;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Status;
import seedu.address.model.person.enums.ApplicantState;
import seedu.address.model.person.enums.InterviewerState;
import seedu.address.model.person.enums.Type;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_REMARK = "She likes aardvarks.";

    private Name name;
    private Phone phone;
    private Email email;
    private Remark remark;
    private Set<Tag> tags;
    private String type;
    private String status;

    /**
     * Initializes the PersonBuilder.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        type = Type.PERSON.toString();
        status = "";
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        remark = personToCopy.getRemark();
        type = personToCopy.getPersonType();
        tags = new HashSet<>(personToCopy.getTags());
        if (type.equals("APPLICANT")) {
            tags.add(new Tag("Applicant"));
        } else if (type.equals("INTERVIEWER")) {
            tags.add(new Tag("Interviewer"));
        }
        status = personToCopy.getStatus();
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
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(Status status) {
        this.status = status.toString();
        return this;
    }

    /**
     * Builds the {@code Person} with the given {@code Name}, {@code Phone},
     * {@code Email},{@code Remark} and {@code Tags}.
     */
    public Person build() {
        return new Person(name, phone, email, remark, tags);
    }

    /**
     * Builds the {@code Applicant} with the given {@code Name}, {@code Phone},
     * {@code Email},{@code Remark}, {@code Status} and {@code Tags}.
     */
    public Applicant build_applicant() {
        return new Applicant(name, phone, email, remark, new ApplicantStatus(ApplicantState.STAGEONE.toString()), tags);
    }

    /**
     * Builds the {@code Interviewer} with the given {@code Name}, {@code Phone},
     * {@code Email},{@code Remark}, {@code Status} and {@code Tags}.
     */
    public Interviewer build_interviewer() {
        return new Interviewer(name, phone, email, remark, new InterviewerStatus(InterviewerState.FREE.toString()),
                tags);
    }
}
