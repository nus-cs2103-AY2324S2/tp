package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Points;
import seedu.address.model.person.orders.Order;
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
    public static final String DEFAULT_MEMBERSHIP = "T1";

    public static final String DEFAULT_POINTS = "0";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Membership membership;
    private Set<Tag> tags;
    private Points points;
    private ArrayList<Order> orders;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        membership = new Membership(DEFAULT_MEMBERSHIP);
        tags = new HashSet<>();
        points = new Points(DEFAULT_POINTS);
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
        membership = personToCopy.getMembership();
        tags = new HashSet<>(personToCopy.getTags());
        points = personToCopy.getPoints();
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
     * Parses the {@code orders} into a {@code ArrayList<Order>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withOrders(String ... orders) {
        this.orders = SampleDataUtil.getOrderArrayList(orders);
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
     * Sets the {@code Points} of the {@code Points} that we are building.
     */
    public PersonBuilder withPoints(String points) {
        this.points = new Points(points);
        return this;
    }

    /**
     * Sets the {@code Membership} of the {@code Person} that we are building.
     */

    public PersonBuilder withMembership(String membership) {
        this.membership = new Membership(membership);
        return this;
    }

    /**
     * Builds and returns a {@code Person} with the current attributes set in the {@code PersonBuilder}.
     *
     * This method provides a way to construct a new {@code Person} instance using the builder pattern. Attributes set
     * in the builder are used to instantiate the {@code Person}. This includes the person's name, phone number, email,
     * address, membership status, set of tags, and points. If an attribute has not been explicitly set in the builder,
     * the default value for that attribute is used.
     *
     * @return A new {@code Person} instance with attributes specified in the {@code PersonBuilder}.
     */
    public Person build() {
        return new Person(name, phone, email, address, membership, tags, points, orders);

    }
}
