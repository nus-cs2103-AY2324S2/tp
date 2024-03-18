package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.house.House;
import seedu.address.model.house.PostalCode;
import seedu.address.model.tag.Tag;
/**
 * Represents a buyer in the address book.
 */
public class Buyer extends Person {

    private final House house;

    /**
     * Constructs a new Buyer instance.
     *
     * @param name        The name of the buyer.
     * @param phone       The phone number of the buyer.
     * @param email       The email address of the buyer.
     * @param housingtype The type of housing the buyer wants.
     * @param tags        The tags associated with the buyer.
     */
    public Buyer(Name name, Phone phone, Email email, String housingtype, Set<Tag> tags) {
        super(name, phone, email, housingtype, tags);
        this.house = null;
    }

    public Buyer(Name name, Phone phone, Email email, String housingtype, House house, Set<Tag> tags) {
        super(name, phone, email, housingtype, tags);
        this.house = house;
    }
}

