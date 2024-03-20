package seedu.realodex.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.remark.Remark;
import seedu.realodex.model.tag.Tag;
import seedu.realodex.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_INCOME = "20000";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_FAMILY = "4";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final Tag DEFAULT_TAG = new Tag("buyer");

    private Name name;
    private Phone phone;
    private Income income;
    private Email email;
    private Address address;
    private Family family;
    private Set<Tag> tags;
    private Remark remark;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        income = new Income(DEFAULT_INCOME);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        family = new Family(DEFAULT_FAMILY);
        tags = new HashSet<>();
        tags.add(DEFAULT_TAG);
        remark = new Remark("");
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        income = personToCopy.getIncome();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        family = personToCopy.getFamily();
        tags = new HashSet<>(personToCopy.getTags());
        remark = personToCopy.getRemark();
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
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Income} of the {@code Person} that we are building.
     */
    public PersonBuilder withIncome(String income) {
        this.income = new Income(income);
        return this;
    }

    /**
     * Sets the {@code Family} of the {@code Person} that we are building.
     */
    public PersonBuilder withFamily(String family) {
        this.family = new Family(family);
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

    public Person build() {
        return new Person(name, phone, income, email, address, family, tags, remark);
    }

}
