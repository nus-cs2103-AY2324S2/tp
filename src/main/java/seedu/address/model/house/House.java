package seedu.address.model.house;

/**
 * Represents a House.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class House {
    // I think we should take note this part, I believe that we should separate out House and Housing Type
    public static final String MESSAGE_CONSTRAINTS = "Housing types can only be HDB, Condominium or Landed.";
    public static final String[] VALIDATION_REGEX = {"HDB", "Condominium", "Landed"};
    public final PostalCode postalCode;
    public final Street street;
    public final UnitNumber unitNumber;

    /**
     * Constructs a House.
     *
     * @param unitNumber The unit number of the house.
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     */
    public House(UnitNumber unitNumber, PostalCode postalCode, Street street) {
        this.postalCode = postalCode;
        this.street = street;
        this.unitNumber = unitNumber;
    }

    /**
     * Constructs a House with a different unit number for editing purposes.
     *
     * @param unitNumber The new unit number of the house.
     * @param house      The existing house object.
     */
    public House(UnitNumber unitNumber, House house) {
        this.unitNumber = unitNumber;
        this.postalCode = house.getPostalCode();
        this.street = house.getStreet();
    }

    /**
     * Constructs a House with a different postal code for editing purposes.
     *
     * @param postalCode The new postal code of the house.
     * @param house      The existing house object.
     */
    public House(PostalCode postalCode, House house) {
        this.unitNumber = house.getUnitNumber();
        this.postalCode = house.getPostalCode();
        this.street = house.getStreet();
    }

    /**
     * Constructs a House with a different street for editing purposes.
     *
     * @param street The new street of the house.
     * @param house  The existing house object.
     */
    public House(Street street, House house) {
        this.unitNumber = house.getUnitNumber();
        this.postalCode = house.getPostalCode();
        this.street = street;
    }

    /**
     * Constructs a copy of the given house for editing purposes.
     *
     * @param house The house to copy.
     */
    public House(House house) {
        this.unitNumber = house.getUnitNumber();
        this.postalCode = house.getPostalCode();
        this.street = house.getStreet();
    }

    /**
     * Retrieves the unit number of the house.
     *
     * @return The unit number of the house.
     */
    public UnitNumber getUnitNumber() {
        return unitNumber;
    }

    /**
     * Retrieves the postal code of the house.
     *
     * @return The postal code of the house.
     */
    public PostalCode getPostalCode() {
        return postalCode;
    }

    /**
     * Retrieves the street of the house.
     *
     * @return The street of the house.
     */
    public Street getStreet() {
        return street;
    }

    /**
     * Checks if the given name is a valid housing type.
     *
     * @param name The name to check.
     * @return True if the name is a valid housing type, false otherwise.
     */
    public static boolean isValidName(String name) {
        for (String element : VALIDATION_REGEX) {
            if (element.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this house is equal to another object.
     *
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof House)) {
            return false;
        }

        House otherStreet = (House) other;
        return this.toString().equals(otherStreet.toString());
    }

    /**
     * Returns a string representation of the house.
     *
     * @return A string representation of the house.
     */
    @Override
    public String toString() {
        return "House Address: " + this.unitNumber + ", " + this.street + ", S" + this.postalCode;
    }
}
