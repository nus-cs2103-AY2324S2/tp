package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.house.Block;
import seedu.address.model.house.House;
import seedu.address.model.house.Landed;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;

/**
 * Jackson-friendly version of {@link House}
 */
public class JsonAdaptedHouse {
    private final String block;
    private final String level;
    private final String postalCode;
    private final String street;
    private final String unitNumber;
    private final String type; // "NonLanded" or "Landed": This is different from housingType.

    /**
     * Constructs a {@code JsonAdaptedHouse} with the given house details.
     */
    @JsonCreator
    public JsonAdaptedHouse(@JsonProperty("type") String type,
                            @JsonProperty("block") String block,
                            @JsonProperty("level") String level,
                            @JsonProperty("postalCode") String postalCode,
                            @JsonProperty("street") String street,
                            @JsonProperty("unitNumber") String unitNumber) {
        this.type = type;
        this.block = block;
        this.level = level;
        this.postalCode = postalCode;
        this.street = street;
        this.unitNumber = unitNumber;
    }

    /**
     * Converts a given {@code House} into this class for Jackson use.
     */
    public JsonAdaptedHouse(House source) {
        if (source instanceof NonLanded) {
            NonLanded nonLanded = (NonLanded) source;
            this.block = nonLanded.getBlock() == null ? null : nonLanded.getBlock().value;
            this.level = nonLanded.getLevel() == null ? null : nonLanded.getLevel().value;
            this.type = "NonLanded";
        } else {
            this.block = null;
            this.level = null;
            this.type = "Landed";
        }
        this.postalCode = source.getPostalCode().value;
        this.street = source.getStreet().value;
        this.unitNumber = source.getUnitNumber().value;
    }

    /**
     * Converts this Jackson-friendly adapted house object into the model's {@code House} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted house.
     */
    public House toModelType() throws IllegalValueException {
        PostalCode modelPostalCode = new PostalCode(postalCode);
        Street modelStreet = new Street(street);
        UnitNumber modelUnitNumber = new UnitNumber(unitNumber);

        if ("NonLanded".equals(type)) {
            Block modelBlock = block != null ? new Block(block) : null;
            Level modelLevel = level != null ? new Level(level) : null;
            // Non-Landed: Got Block, Got Level
            if (modelBlock != null) {
                return new NonLanded(modelBlock, modelLevel, modelPostalCode, modelStreet, modelUnitNumber);
                // Non-Landed: No Block, Got Level
            } else {
                return new NonLanded(modelLevel, modelPostalCode, modelStreet, modelUnitNumber);
            }
        } else if ("Landed".equals(type)) {
            return new Landed(modelUnitNumber, modelPostalCode, modelStreet);
        } else {
            throw new IllegalValueException("Unknown house type");
        }
    }
}
