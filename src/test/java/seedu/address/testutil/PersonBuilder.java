package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * Represents a builder class for creating instances of the Person class.
 * This class provides methods for setting the attributes of a Person object.
 * The builder pattern is used to allow for a flexible and fluent way of constructing Person objects.
 *
 * @param <T> The type of the builder class.
 */
public abstract class PersonBuilder<T extends PersonBuilder<T>> {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "some remarks";

    protected Id id;
    protected Name name;
    protected Phone phone;
    protected Email email;
    protected Address address;
    protected Remark remark;
    protected Set<Tag> tags;

    /**
     * Constructs a new PersonBuilder with default values.
     */
    public PersonBuilder() {
        id = Id.generateNextId();
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        id = personToCopy.getId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        remark = personToCopy.getRemark();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    /**
     * Sets the id of the person that we are building.
     *
     * @param id The id of the person.
     * @return The updated PersonBuilder object.
     */
    public T withId(int id) {
        this.id = Id.generateId(id);
        return self();
    }

    /**
     * Sets the name of the person that we are building.
     *
     * @param name The name of the person.
     * @return The updated PersonBuilder object.
     */
    public T withName(String name) {
        this.name = new Name(name);
        return self();
    }

    /**
     * Sets the phone number of the person that we are building.
     *
     * @param phone The phone number of the person.
     * @return The updated PersonBuilder object.
     */
    public T withPhone(String phone) {
        this.phone = new Phone(phone);
        return self();
    }

    /**
     * Sets the email of the person that we are building.
     *
     * @param email The email of the person.
     * @return The updated PersonBuilder object.
     */
    public T withEmail(String email) {
        this.email = new Email(email);
        return self();
    }

    /**
     * Sets the address of the person that we are building.
     *
     * @param address The address of the person.
     * @return The updated PersonBuilder object.
     */
    public T withAddress(String address) {
        this.address = new Address(address);
        return self();
    }

    /**
     * Sets the remark of the person that we are building.
     *
     * @param remark The remark of the person.
     * @return The updated PersonBuilder object.
     */
    public T withRemark(String remark) {
        this.remark = new Remark(remark);
        return self();
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public T withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return self();
    }

    /**
     * Builds the person object.
     *
     * @return The person object.
     */
    public abstract Person build();
}
