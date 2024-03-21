package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Client extends Person {
    private final Products products;
    private final String preferences;

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address, Remark remark,
                  Set<Tag> tags, Products products, String preferences) {
        super(name, phone, email, address, remark, tags);
        this.products = products;
        this.preferences = preferences;
    }

    /**
     * Every field must be present and not null.
     */
    public Client(Id id, Name name, Phone phone, Email email, Address address, Remark remark,
                  Set<Tag> tags, Products products, String preferences) {
        super(id, name, phone, email, address, remark, tags);
        this.products = products;
        this.preferences = preferences;
    }


    /**
     * Returns the products preferred by the client.
     *
     * @return The products preferred by the client.
     */
    public Products getProducts() {
        return this.products;
    }

    /**
     * Returns the preferences of the client.
     *
     * @return The preferences of the client.
     */
    public String getPreferences() {
        return this.preferences;
    }

    @Override
    public String getRole() {
        return "Client";
    }

    /**
     * Checks if this client is the same as another person.
     * Two clients are considered the same if they have the same attributes.
     *
     * @param otherPerson The person to compare with.
     * @return True if the clients are the same, false otherwise.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (!super.isSamePerson(otherPerson)) {
            return false;
        }
        return otherPerson instanceof Client;
    }

    /**
     * Checks if this client is the same as another client.
     * Two clients are considered the same if they have the same attributes.
     *
     * @param other The client to compare with.
     * @return True if the clients are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Client
                && super.equals(other)
                && products.equals(((Client) other).products)
                && preferences.equals(((Client) other).preferences));
    }

    /**
     * Returns the hash code of this client.
     *
     * @return The hash code of this client.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products, preferences);
    }

    /**
     * Returns the string representation of this client.
     *
     * @return The string representation of this client.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .add("products", products)
                .add("preferences", preferences)
                .toString();
    }
}
