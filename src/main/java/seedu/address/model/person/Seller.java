package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.house.House;
import seedu.address.model.tag.Tag;

/**
 * Represents a seller in the address book.
 */
public class Seller extends Person {
    private final ArrayList<House> houses;

    /**
     * Constructs a new Seller instance.
     *
     * @param name        The name of the seller.
     * @param phone       The phone number of the seller.
     * @param email       The email address of the seller.
     * @param housingType The housing type the seller has
     * @param houses      The houses the seller has (modified to accept a list of houses)
     * @param tags        The tags associated with the seller.
     */
    public Seller(Name name, Phone phone, Email email, String housingType, ArrayList<House> houses, Set<Tag> tags) {
        super(name, phone, email, housingType, tags);
        this.houses = houses;
    }

    /**
     * Adds a new house to the seller's list of houses. This will only be implemented when there is a add house command
     * @param house The new house to add.
     */
    public void addHouse(House house) {
        this.houses.add(house);
    }

    /**
     * Returns the list of houses associated with this seller.
     * @return An ArrayList containing House objects.
     */
    public ArrayList<House> getHouses() {
        return new ArrayList<>(houses); // Returns a copy of the houses list
    }
}
