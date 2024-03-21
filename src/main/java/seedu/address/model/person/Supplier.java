package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a supplier in the address book.
 * Subclass of the Person class.
 * Contains information about the supplier's products and terms of service.
 */
public class Supplier extends Person {
    private final Products products;
    private final TermsOfService termsOfService;

    /**
     * Every field must be present and not null.
     */
    public Supplier(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                    Products products, TermsOfService termsOfService) {
        super(name, phone, email, address, remark, tags);
        this.products = products;
        this.termsOfService = termsOfService;
    }

    public Supplier(Id id, Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                    Products products, TermsOfService termsOfService) {
        super(id, name, phone, email, address, remark, tags);
        this.products = products;
        this.termsOfService = termsOfService;
    }


    /**
     * Returns the products offered by the supplier.
     *
     * @return The products offered by the supplier.
     */
    public Products getProducts() {
        return this.products;
    }

    /**
     * Returns the terms of service provided by the supplier.
     *
     * @return The terms of service provided by the supplier.
     */
    public TermsOfService getTermsOfService() {
        return termsOfService;
    }

    public String getRole() {
        return "Supplier";
    }

    /**
     * Checks if this supplier is the same as another person.
     * Two suppliers are considered the same if they have the same attributes.
     *
     * @param otherPerson The person to compare with.
     * @return True if the suppliers are the same, false otherwise.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (!super.isSamePerson(otherPerson)) {
            return false;
        }
        return otherPerson instanceof Supplier;
    }

    /**
     * Checks if this supplier is equal to another object.
     * Two suppliers are considered equal if they have the same attributes.
     *
     * @param other The object to compare with.
     * @return True if the suppliers are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Supplier
                && super.equals(other)
                && products.equals(((Supplier) other).products)
                && termsOfService.equals(((Supplier) other).termsOfService));
    }

    /**
     * Returns the hash code of the supplier.
     *
     * @return The hash code of the supplier.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products, termsOfService);
    }

    /**
     * Returns a string representation of the supplier.
     *
     * @return A string representation of the supplier.
     */
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
                .add("termsOfService", termsOfService)
                .toString();
    }
}
