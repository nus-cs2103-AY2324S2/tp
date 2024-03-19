package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.house.PostalCode;
import seedu.address.model.tag.Tag;
/**
 * Represents a buyer in the address book.
 */
public class Buyer extends Person {

    /**
     * Constructs a new Buyer instance.
     *
     * @param name       The name of the buyer.
     * @param phone      The phone number of the buyer.
     * @param email      The email address of the buyer.
     * @param address    The address of the buyer.
     * @param postalCode The postal code of the buyer's address.
     * @param tags       The tags associated with the buyer.
     */
    public Buyer(Name name, Phone phone, Email email, Address address, PostalCode postalCode, Set<Tag> tags) {
        super(name, phone, email, address, postalCode, tags);
    }
}

