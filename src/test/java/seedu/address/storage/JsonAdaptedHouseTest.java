package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.house.Block;
import seedu.address.model.house.Landed;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;

public class JsonAdaptedHouseTest {
    private static final String VALID_TYPE_NONLANDED = "NonLanded";
    private static final String VALID_TYPE_LANDED = "Landed";
    private static final String VALID_BLOCK = "10A";
    private static final String VALID_LEVEL = "15";
    private static final String VALID_POSTALCODE = "654321";
    private static final String VALID_STREET = "Orchid Street";
    private static final String VALID_UNITNUMBER = "150";

    @Test
    public void toModelType_validNonLandedDetails_returnsHouse() throws Exception {
        JsonAdaptedHouse house = new JsonAdaptedHouse(VALID_TYPE_NONLANDED, VALID_BLOCK, VALID_LEVEL, VALID_POSTALCODE,
                VALID_STREET, VALID_UNITNUMBER);
        NonLanded expectedHouse = new NonLanded(new Block(VALID_BLOCK), new Level(VALID_LEVEL),
                new PostalCode(VALID_POSTALCODE),
                new Street(VALID_STREET), new UnitNumber(VALID_UNITNUMBER));
        assertEquals(expectedHouse, house.toModelType());
    }

    @Test
    public void toModelType_validLandedDetails_returnsHouse() throws Exception {
        JsonAdaptedHouse house = new JsonAdaptedHouse(VALID_TYPE_LANDED, null, null,
                VALID_POSTALCODE, VALID_STREET, VALID_UNITNUMBER);
        Landed expectedHouse = new Landed(new UnitNumber(VALID_UNITNUMBER),
                new PostalCode(VALID_POSTALCODE), new Street(VALID_STREET));
        assertEquals(expectedHouse, house.toModelType());
    }
}
