package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UnitNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UnitNumber(null));
    }

    @Test
    public void constructor_invalidUnitNumber_throwsIllegalArgumentException() {
        String invalidUnitNumber = "1"; // Not 2 digits
        assertThrows(IllegalArgumentException.class, () -> new UnitNumber(invalidUnitNumber));
    }

    @Test
    public void isValidUnitNumber() {
        // null unit number
        assertThrows(NullPointerException.class, () -> UnitNumber.isValidUnitNumber(null));

        // invalid unit numbers
        assertFalse(UnitNumber.isValidUnitNumber("")); // empty string
        assertFalse(UnitNumber.isValidUnitNumber(" ")); // spaces only
        assertFalse(UnitNumber.isValidUnitNumber("9")); // less than 2 digits
        assertFalse(UnitNumber.isValidUnitNumber("123")); // more than 2 digits
        assertFalse(UnitNumber.isValidUnitNumber("ab")); // non-numeric
        assertFalse(UnitNumber.isValidUnitNumber("1a")); // alphabets within digits
        assertFalse(UnitNumber.isValidUnitNumber(" 12")); // spaces before digits
        assertFalse(UnitNumber.isValidUnitNumber("12 ")); // spaces after digits

        // valid unit numbers
        assertTrue(UnitNumber.isValidUnitNumber("10")); // exactly 2 digits
        assertTrue(UnitNumber.isValidUnitNumber("99")); // exactly 2 digits
    }

    @Test
    public void equals() {
        UnitNumber unitNumber = new UnitNumber("12");

        // same values -> returns true
        assertTrue(unitNumber.equals(new UnitNumber("12")));

        // same object -> returns true
        assertTrue(unitNumber.equals(unitNumber));

        // null -> returns false
        assertFalse(unitNumber.equals(null));

        // different types -> returns false
        assertFalse(unitNumber.equals(5.0f));

        // different unit number -> returns false
        assertFalse(unitNumber.equals(new UnitNumber("34")));
    }
}
