package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Staff;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class StaffBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TAG = "staff";
    public static final String DEFAULT_SALARY = "$50/hr";
    public static final String DEFAULT_EMPLOYMENT = "part-time";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Salary salary;
    private Employment employment;
    private Set<Tag> tags;
    private Tag tag;

    /**
     * Creates a {@code StaffBuilder} with the default details.
     */
    public StaffBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        tag = new Tag(DEFAULT_TAG);
        tags.add(tag);
        salary = new Salary(DEFAULT_SALARY);
        employment = new Employment(DEFAULT_EMPLOYMENT);
    }

    /**
     * Initializes the StaffBuilder with the data of {@code personToCopy}.
     */
    public StaffBuilder(Staff personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        tag = new Tag(DEFAULT_TAG);
        tags.add(tag);
        salary = personToCopy.getSalary();
        employment = personToCopy.getEmployment();
    }

    /**
     * Sets the {@code Name} of the {@code Staff} that we are building.
     */
    public StaffBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Staff} that we are building.
     */
    public StaffBuilder withTags(String ... tags) {
        /*
        this.tags = SampleDataUtil.getTagSet(tags);
         */
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Staff} that we are building.
     */
    public StaffBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Staff} that we are building.
     */
    public StaffBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Staff} that we are building.
     */
    public StaffBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code Employment} of the {@code Staff} that we are building.
     */
    public StaffBuilder withEmployment(String employment) {
        this.employment = new Employment(employment);
        return this;
    }

    public Staff build() {
        return new Staff(name, phone, email, address, tags, salary, employment);
    }

}
