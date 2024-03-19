package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

public class Client extends Person {
    private final Products products;
    private final String preferences;

    public Client(Name name, Phone phone, Email email, Address address, Remark remark,
                  Set<Tag> tags, Products products, String preferences) {
        super(name, phone, email, address, remark, tags);
        this.products = products;
        this.preferences = preferences;
    }

    public Products getProducts() {
        return this.products;
    }

    public String getPreferences() {
        return this.preferences;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Client
                && super.equals(other)
                && products.equals(((Client) other).products)
                && preferences.equals(((Client) other).preferences));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products, preferences);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
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