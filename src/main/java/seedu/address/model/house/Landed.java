package seedu.address.model.house;

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
        return unitNumber.toString() + " " + street.toString() + postalCode.toString();
    }
}
