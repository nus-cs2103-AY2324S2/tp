package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import seedu.address.model.person.Person;

/**
 * Represents a House.
 */
public class House {
    public static final String MESSAGE_CONSTRAINTS =
            "Housing types can only be HDB, Condominium or Landed.";
    public static final String[] VALIDATION_REGEX = {"hdb", "condominium", "landed"};
    public final PostalCode postalCode;
    public final Street street;
    public final UnitNumber unitNumber;

    /**
     * Constructs a {@code Level}.
     *
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     */
    public House(UnitNumber unitNumber, PostalCode postalCode, Street street) {
        this.postalCode = postalCode;
        this.street = street;
        this.unitNumber = unitNumber;
    }

    public House(UnitNumber unitNumber, House house) {
        this.unitNumber = unitNumber;
        this.postalCode = house.getPostalCode();
        this.street = house.getStreet();
    }
    public House(PostalCode postalCode, House house) {
        this.unitNumber = house.getUnitNumber();
        this.postalCode = house.getPostalCode();
        this.street = house.getStreet();
    }
    public House(Street street, House house) {
        this.unitNumber = house.getUnitNumber();
        this.postalCode = house.getPostalCode();
        this.street = street;
    }

    public House(House house) {
        this.unitNumber = house.getUnitNumber();
        this.postalCode = house.getPostalCode();
        this.street = house.getStreet();
    }

    public UnitNumber getUnitNumber() {
        return unitNumber;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public Street getStreet() {
        return street;
    }

    public static boolean isValidName(String name) {
        for (String element : VALIDATION_REGEX) {
            if (element.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "HOMELESS";
    }


}
