package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.house.House;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;
import seedu.address.model.tag.Tag;

/**
 * Represents a seller in the address book.
 */
public class Seller extends Person {

    private final Street street;
    private final House house;
    private final PostalCode postalCode;
    private final UnitNumber unitNumber;

    /**
     * Constructs a new Seller instance.
     *
     * @param name        The name of the seller.
     * @param phone       The phone number of the seller.
     * @param email       The email address of the seller.
     * @param housingtype The housing type the seller has
     * @param street      The street of the seller's house
     * @param postalCode  The postal code of the seller's address.
     * @param tags        The tags associated with the seller.
     */
    public Seller(Name name, Phone phone, Email email, String housingtype, House house, Street street,
                  PostalCode postalCode, UnitNumber unitNumber, Set<Tag> tags) {
        super(name, phone, email, housingtype, tags);
        this.house = house;
        this.street = street;
        this.postalCode = postalCode;
        this.unitNumber = unitNumber;
    }
}

