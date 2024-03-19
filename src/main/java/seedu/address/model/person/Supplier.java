package seedu.address.model.person;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

public class Supplier extends Person {
    private final Products products;
    private final TermsOfService termsOfService;

    public Supplier(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                    Products products, TermsOfService termsOfService) {
        super(name, phone, email, address, remark, tags);
        this.products = products;
        this.termsOfService = termsOfService;
    }

    public Products getProducts() {
        return this.products;
    }

    public TermsOfService getTermsOfService() {
        return termsOfService;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Supplier
                && super.equals(other)
                && products.equals(((Supplier) other).products)
                && termsOfService.equals(((Supplier) other).termsOfService));
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products, termsOfService);
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
                .add("termsOfService", termsOfService)
                .toString();
    }
}
