package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_FIRST_PARENT_PHONE = "85355255";
    public static final String DEFAULT_SECOND_PARENT_PHONE = "91234544";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_STUDENT_ID = "10001";
    public static final String DEFAULT_CLASS = "6 A";

    private Name name;
    private Phone firstParentPhone;
    private Phone secondParentPhone;
    private Email email;
    private Address address;
    private StudentId studentId;
    private FormClass formClass;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        firstParentPhone = new Phone(DEFAULT_FIRST_PARENT_PHONE);
        secondParentPhone = new Phone(DEFAULT_SECOND_PARENT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        tags = new HashSet<>();
        formClass = new FormClass(DEFAULT_CLASS);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        firstParentPhone = personToCopy.getParentPhoneOne();
        secondParentPhone = personToCopy.getParentPhoneTwo();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        studentId = personToCopy.getStudentId();
        tags = new HashSet<>(personToCopy.getTags());
        formClass = personToCopy.getFormClass();
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
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code First parent phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withFirstParentPhone(String firstParentPhone) {
        this.firstParentPhone = new Phone(firstParentPhone);
        return this;
    }

    /**
     * Sets the {@code Second parent phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withSecondParentPhone(String secondParentPhone) {
        this.secondParentPhone = new Phone(secondParentPhone);
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code StudentId} that we are building.
     */
    public PersonBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
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
     * Sets the {@code Classroom} of the {@code Person} that we are building.
     */
    public PersonBuilder withClass(String formClass) {
        this.formClass = new FormClass(formClass);
        return this;
    }

    /**
     * Builds the person as designated by the PersonBuilder.
     * @returns A person based on the fields of the PersonBuilder.
     */
    public Person build() {

        return new Person(name, firstParentPhone, secondParentPhone, email, address, studentId, tags, formClass);
    }

}
