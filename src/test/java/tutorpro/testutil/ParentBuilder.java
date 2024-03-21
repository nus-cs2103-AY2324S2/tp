package tutorpro.testutil;

import java.util.Set;

import tutorpro.model.person.Address;
import tutorpro.model.person.Email;
import tutorpro.model.person.Name;
import tutorpro.model.person.Phone;
import tutorpro.model.person.parent.Parent;
import tutorpro.model.person.student.Student;
import tutorpro.model.tag.Tag;
import tutorpro.model.util.SampleDataUtil;

/**
 * A utility class to help with building Parent objects.
 */
public class ParentBuilder {
    public static final String DEFAULT_NAME = "Xin Yuan Senior";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "xys@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private Set<Student> students;

    /**
     * Creates a {@code ParentBuilder} with the default details.
     */
    public ParentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = Set.of();
        students = Set.of();
    }

    /**
     * Initializes the ParentBuilder with the data of {@code parentToCopy}.
     */
    public ParentBuilder(Parent parentToCopy) {
        name = parentToCopy.getName();
        phone = parentToCopy.getPhone();
        email = parentToCopy.getEmail();
        address = parentToCopy.getAddress();
        tags = parentToCopy.getTags();
        students = parentToCopy.getStudents();
    }

    /**
     * Sets the {@code Name} of the {@code Parent} that we are building.
     */
    public ParentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Parent} that we are building.
     */
    public ParentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Parent} that we are building.
     */
    public ParentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Parent} that we are building.
     */
    public ParentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Parent} that we are building.
     */
    public ParentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code students} into a {@code Set<Student>} and set it to the {@code Parent} that we are building.
     */
    public ParentBuilder withStudents(Student... students) {
        this.students = SampleDataUtil.getStudentSet(students);
        return this;
    }

    public Parent build() {
        return new Parent(name, phone, email, address, tags, students);
    }

}
