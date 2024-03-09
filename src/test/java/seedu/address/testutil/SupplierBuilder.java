package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Person objects.
 */
public class SupplierBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TAG = "staff";
    public static final String DEFAULT_PRODUCT = "pooch food";
    public static final String DEFAULT_PRICE = "$50/bag";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Price price;
    private Product product;
    private Set<Tag> tags;
    private Tag tag;

    /**
     * Creates a {@code SupplierBuilder} with the default details.
     */
    public SupplierBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        tag = new Tag(DEFAULT_TAG);
        tags.add(tag);
        product = new Product(DEFAULT_PRODUCT);
        price = new Price(DEFAULT_PRICE);
    }

    /**
     * Initializes the SupplierBuilder with the data of {@code personToCopy}.
     */
    public SupplierBuilder(Supplier personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        tag = new Tag(DEFAULT_TAG);
        tags.add(tag);
        product = personToCopy.getProduct();
        price = personToCopy.getPrice();
    }

    /**
     * Sets the {@code Name} of the {@code Supplier} that we are building.
     */
    public SupplierBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Supplier} that we are building.
     */
    public SupplierBuilder withTags(String ... tags) {
        /*
        this.tags = SampleDataUtil.getTagSet(tags);
         */
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Supplier} that we are building.
     */
    public SupplierBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Supplier} that we are building.
     */
    public SupplierBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Supplier} that we are building.
     */
    public SupplierBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Product} of the {@code Supplier} that we are building.
     */
    public SupplierBuilder withProduct(String product) {
        this.product = new Product(product);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Supplier} that we are building.
     */
    public SupplierBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    public Supplier build() {
        return new Supplier(name, phone, email, address, tags, product, price);
    }

}
