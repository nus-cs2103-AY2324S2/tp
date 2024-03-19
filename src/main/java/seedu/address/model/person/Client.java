package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class Client extends Person {
    private Products products = new Products(new ArrayList<>());
    private String preferences;

    public Client(Name name, Phone phone, Email email, Address address, Remark remark,
            Set<Tag> tags, List<String> products, String preferences) {
        super(name, phone, email, address, remark, tags);
        this.products.addProducts(products);
        this.preferences = preferences;
    }

    public Products getProducts() {
        return this.products;
    }

    public String getPreferences() {
        return preferences;
    }

}