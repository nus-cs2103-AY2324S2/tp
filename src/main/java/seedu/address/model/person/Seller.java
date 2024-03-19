package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.house.House;

import seedu.address.model.tag.Tag;

/**
 * Represents a seller in the address book.
 */
public class Seller extends Person {

    private final House house;


    /**
     * Constructs a new Seller instance.
     *
     * @param name        The name of the seller.
     * @param phone       The phone number of the seller.
     * @param email       The email address of the seller.
     * @param housingtype The housing type the seller has
     * @param tags        The tags associated with the seller.
     */
    public Seller(Name name, Phone phone, Email email, String housingtype, House house, Set<Tag> tags) {
        super(name, phone, email, housingtype, tags);
        this.house = house;
    }
}

