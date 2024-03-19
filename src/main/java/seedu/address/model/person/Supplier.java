package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class Supplier extends Person {
    private Products products = new Products(new ArrayList<>());
    private TermsOfService termsOfService;

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
}
