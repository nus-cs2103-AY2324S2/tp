package seedu.address.model.house;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a landed house.
 */
public class Landed extends House {

    /**
     * Constructs a Landed house.
     *
     * @param unitNumber The unit number of the house.
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     */
    public Landed(UnitNumber unitNumber, PostalCode postalCode, Street street) {
        super(unitNumber, postalCode, street);
    }

    /**
     * Returns a string representation of the landed house.
     *
     * @return A string representation of the landed house.
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        // For now, it just appends the super class's toString method.
        return "Landed House: " + builder.toString() + ", " + super.toString();
    }
}
