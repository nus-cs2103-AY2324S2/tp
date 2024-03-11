package seedu.address.testutil;

import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_SEX = "f";
    public static final String DEFAULT_EMPLOYMENT_TYPE = "ft";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BANK_ADDRESS = "12345678";

    private Name name;
    private Phone phone;
    private Sex sex;
    private EmploymentType employmentType;
    private Address address;
    private BankDetails bankDetails;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        sex = new Sex(DEFAULT_SEX);
        employmentType = new EmploymentType(DEFAULT_EMPLOYMENT_TYPE);
        address = new Address(DEFAULT_ADDRESS);
        bankDetails = new BankDetails(DEFAULT_BANK_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        address = personToCopy.getAddress();
        sex = personToCopy.getSex();
        employmentType = personToCopy.getEmploymentType();
        bankDetails = personToCopy.getBankDetails();
        tags = new HashSet<>(personToCopy.getTags());
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Person} that we are building.
     */
    public PersonBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code EmploymentType} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmploymentType(String employmentType) {
        this.employmentType = new EmploymentType(employmentType);
        return this;
    }
    
    /**
     * Sets the {@code BankDetails} of the {@code Person} that we are building.
     */
    public PersonBuilder withBankDetails(String bankDetails) {
        this.bankDetails = new BankDetails(bankDetails);
        return this;
    }

    public Person build() {
        return new Person(name, phone, sex, employmentType, address, bankDetails, tags);
    }

}
