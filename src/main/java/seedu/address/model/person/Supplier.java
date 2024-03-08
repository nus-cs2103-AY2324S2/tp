package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Supplier extends Person{
    // Data fields
    private final Product product;
    private final Price price;

    public Supplier(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                    Product product, Price price) {
        super(name, phone, email, address, tags);
        requireAllNonNull(product, price);
        this.product = product;
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public Product getProduct() {
        return product;
    }

    /**
     * Returns true if both staffs have the same identity and data fields.
     * This defines a stronger notion of equality between two staff.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Supplier)) {
            return false;
        }

        Supplier otherPerson = (Supplier) other;
        return super.equals(otherPerson)
                && product.equals(otherPerson.product)
                && price.equals(otherPerson.price);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(super.hashCode(), product, price);
    }

    @Override
    public String toString() {
        return super.toString() + new ToStringBuilder(this)
                .add("product", product)
                .add("price", price)
                .toString();
    }
}
