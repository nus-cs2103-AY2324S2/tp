package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_COMPANY = "Bees bees";
    public static final boolean DEFAULT_ISFAVOURITE = false;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Company company;
    private boolean isFavourite = false;
    private Set<Tag> tags;
    private ArrayList<Order> orders;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        company = new Company(DEFAULT_COMPANY);
        isFavourite = DEFAULT_ISFAVOURITE;
        tags = new HashSet<>();
        orders = new ArrayList<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        company = personToCopy.getCompany();
        isFavourite = personToCopy.getIsFavourite();
        tags = new HashSet<>(personToCopy.getTags());
        orders = new ArrayList<>(personToCopy.getOrders());
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
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Person} that we are building.
     */
    public PersonBuilder withCompany(String companyName) {
        this.company = new Company(companyName);
        return this;
    }

    /**
     * Sets the isFavourite of the {@code Person} that we are building.
     */
    public PersonBuilder withFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
        return this;
    }

    /**
     * Set the {@code ArrayList<Order>} of the {@code Person} that we are building.
     */
    public PersonBuilder withOrders(ArrayList<Order> orders) {
        this.orders = orders;
        return this;
    }

    /**
     * Removes the order from the {@code Person} that we are building at the specified index.
     * This mimics the deletion of an order.
     */
    public PersonBuilder withoutOrder(int index) {
        if (index >= 0 && index < this.orders.size()) {
            this.orders.remove(index);
        }
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, company, isFavourite, tags, orders);
    }

}
