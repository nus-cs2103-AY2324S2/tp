package seedu.address.model.house;

/**
 * Represents a non-landed house.
 */
public class NonLanded extends House {

    /** The block of the house. */
    public final Block block;

    /** The level of the house. */
    public final Level level;

    /** The unit number of the house. */
    public final UnitNumber unitNumber;

    /**
     * Constructs a non-landed house with block, level, postal code, street, and unit number.
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
     * Constructs a non-landed house with level, postal code, street, and unit number. For condominiums with no block.
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

    /**
     * Retrieves the block of the house
     *
     * @return The block of the house
     */
    public Block getBlock() {
        return this.block;
    }

    /**
     * Retrieves the level of the house
     *
     * @return The level of the house
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Returns a string representation of the non-landed house.
     *
     * @return A string representation of the non-landed house.
     */
    @Override
    public String toString() {
        String blockLevelInfo = (block != null ? "Block: " + block.value + ", " : "")
                + (level != null ? "Level: " + level.value + ", " : "");
        return "Non-Landed House: " + blockLevelInfo + super.toString();
    }
}
