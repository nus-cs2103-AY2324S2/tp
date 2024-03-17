package seedu.address.model.house;

import seedu.address.model.person.Person;

public class Landed extends House {

    /**
     * Constructs a {@code Level}.
     *
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     */
    public Landed(UnitNumber unitNumber, PostalCode postalCode, Street street) {
        super(unitNumber, postalCode, street);
    }
    @Override
    public String toString() {
        return unitNumber.toString() + " " + street.toString() + postalCode.toString();
    }
}
