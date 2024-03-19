package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.house.PostalCode;
import seedu.address.model.tag.Tag;

/**
 * Represents a seller in the address book.
 */
public class Seller extends Person {

    /**
     * Constructs a new Seller instance.
     *
     * @param name       The name of the seller.
     * @param phone      The phone number of the seller.
     * @param email      The email address of the seller.
     * @param address    The address of the seller.
     * @param postalCode The postal code of the seller's address.
     * @param tags       The tags associated with the seller.
     */
    public Seller(Name name, Phone phone, Email email, Address address, PostalCode postalCode, Set<Tag> tags) {
        super(name, phone, email, address, postalCode, tags);
    }
}
