package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.house.Block;
import seedu.address.model.house.Landed;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;

public class JsonAdaptedHouseTest {
    private static final String INVALID_POSTAL_CODE = "Postal";
    private static final String INVALID_STREET = "";
    private static final String INVALID_UNIT_NUMBER = "Unit 23";
    private static final String VALID_TYPE_NON_LANDED = "NonLanded";
    private static final String VALID_TYPE_LANDED = "Landed";
    private static final String VALID_BLOCK = "10A";
    private static final String VALID_LEVEL = "15";
    private static final String VALID_POSTAL_CODE = "654321";
    private static final String VALID_STREET = "Orchid Street";
    private static final String VALID_UNIT_NUMBER = "150";

    @Test
    public void toModelType_validNonLandedDetails_returnsHouse() throws Exception {
        JsonAdaptedHouse house = new JsonAdaptedHouse(VALID_TYPE_NON_LANDED, VALID_BLOCK,
                VALID_LEVEL, VALID_POSTAL_CODE,
                VALID_STREET, VALID_UNIT_NUMBER);
        NonLanded expectedHouse = new NonLanded(new Block(VALID_BLOCK), new Level(VALID_LEVEL),
                new PostalCode(VALID_POSTAL_CODE),
                new Street(VALID_STREET), new UnitNumber(VALID_UNIT_NUMBER));
        assertEquals(expectedHouse, house.toModelType());
    }

    @Test
    public void toModelType_validLandedDetails_returnsHouse() throws Exception {
        JsonAdaptedHouse house = new JsonAdaptedHouse(VALID_TYPE_LANDED, null, null,
                VALID_POSTAL_CODE, VALID_STREET, VALID_UNIT_NUMBER);
        Landed expectedHouse = new Landed(new UnitNumber(VALID_UNIT_NUMBER),
                new PostalCode(VALID_POSTAL_CODE), new Street(VALID_STREET));
        assertEquals(expectedHouse, house.toModelType());
    }

    @Test
    public void toModelType_nullPostalCode_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", VALID_BLOCK, VALID_LEVEL,
                null, VALID_STREET, VALID_UNIT_NUMBER);
        String expectedMessage = String.format(JsonAdaptedHouse.MISSING_FIELD_MESSAGE_FORMAT,
                PostalCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_invalidPostalCode_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", VALID_BLOCK, VALID_LEVEL,
                INVALID_POSTAL_CODE, VALID_STREET, VALID_UNIT_NUMBER);
        String expectedMessage = String.format(JsonAdaptedHouse.MISSING_FIELD_MESSAGE_FORMAT,
                PostalCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nullStreet_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", VALID_BLOCK, VALID_LEVEL,
                VALID_POSTAL_CODE, null, VALID_UNIT_NUMBER);
        String expectedMessage = String.format(JsonAdaptedHouse.MISSING_FIELD_MESSAGE_FORMAT,
                Street.class.getSimpleName());
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_invalidStreet_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", VALID_BLOCK, VALID_LEVEL,
                VALID_POSTAL_CODE, INVALID_STREET, VALID_UNIT_NUMBER);
        String expectedMessage = String.format(JsonAdaptedHouse.MISSING_FIELD_MESSAGE_FORMAT,
                Street.class.getSimpleName());
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nullUnitNumber_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", VALID_BLOCK, VALID_LEVEL,
                VALID_POSTAL_CODE, VALID_STREET, null);
        String expectedMessage = String.format(JsonAdaptedHouse.MISSING_FIELD_MESSAGE_FORMAT,
                UnitNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_invalidUnitNumber_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", VALID_BLOCK, VALID_LEVEL,
                VALID_POSTAL_CODE, VALID_STREET, INVALID_UNIT_NUMBER);
        String expectedMessage = UnitNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_unknownType_throwsIllegalValueException() {
        JsonAdaptedHouse house = new JsonAdaptedHouse("Treehouse", VALID_BLOCK, VALID_LEVEL,
                VALID_POSTAL_CODE, VALID_STREET, VALID_UNIT_NUMBER);
        String expectedMessage = "Unknown House Type";
        assertThrows(IllegalValueException.class, house::toModelType, expectedMessage);
    }

    @Test
    public void toModelType_nonLandedNoBlockWithLevel_returnsNonLandedHouse() throws Exception {
        JsonAdaptedHouse house = new JsonAdaptedHouse("NonLanded", null, VALID_LEVEL,
                VALID_POSTAL_CODE, VALID_STREET, VALID_UNIT_NUMBER);
        NonLanded expectedHouse = new NonLanded(new Level(VALID_LEVEL), new PostalCode(VALID_POSTAL_CODE),
                new Street(VALID_STREET), new UnitNumber(VALID_UNIT_NUMBER));
        assertEquals(expectedHouse, house.toModelType());
    }
}
