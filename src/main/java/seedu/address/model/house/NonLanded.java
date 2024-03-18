package seedu.address.model.house;

public class NonLanded extends House{
    public final Block block;
    public final Level level;
    public final UnitNumber unitNumber;


    /**
     * Constructs a {@code Level}.
     *
     * @param block      The block of the house.
     * @param level      The level of the house.
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     * @param unitNumber The unit number of the house.
     */
    public NonLanded(Block block, Level level, PostalCode postalCode, Street street, UnitNumber unitNumber) {
        super(unitNumber, postalCode, street);
        this.block = block;
        this.level = level;
        this.unitNumber = unitNumber;
    }

    /**
     * Constructs a {@code Level}.
     *
     * @param level      The level of the house.
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     * @param unitNumber The unit number of the house.
     */
    public NonLanded(Level level, PostalCode postalCode, Street street, UnitNumber unitNumber) {
        super(unitNumber, postalCode, street);
        this.block = null;
        this.level = level;
        this.unitNumber = unitNumber;
    }
}
