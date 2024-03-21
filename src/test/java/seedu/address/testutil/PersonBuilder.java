package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {
    public static final String DEFAULT_COMPANY_NAME = "Google";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_INTERVIEWTIME = "121220221400";
    public static final String DEFAULT_SALARY = "0";
    public static final String DEFAULT_INFO = "";

    private CompanyName companyName;
    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private InterviewTime dateTime;
    private Salary salary;
    private Info info;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        dateTime = new InterviewTime(DEFAULT_INTERVIEWTIME);
        salary = new Salary(DEFAULT_SALARY);
        info = new Info(DEFAULT_INFO);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        companyName = personToCopy.getCompanyName();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        dateTime = personToCopy.getDateTime();
        salary = personToCopy.getSalary();
        info = personToCopy.getInfo();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code CompanyName} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompanyName(String name) {
        this.companyName = new CompanyName(name);
        return this;
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
    public PersonBuilder withTags(String... tags) {
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
     * Sets the {@code Salary} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code Info} of the {@code Person} that we are building.
     */
    public PersonBuilder withInfo(String info) {
        this.info = new Info(info);
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
     * Adds dateTime to PersonBuilder object
     * @param dateTime input
     * @return object
     */
    public PersonBuilder withDateTime(String dateTime) {
        this.dateTime = new InterviewTime(dateTime);
        return this;
    }

    public Person build() {
        return new Person(companyName, name, phone, email, address, dateTime, salary, info, tags);
    }

}
