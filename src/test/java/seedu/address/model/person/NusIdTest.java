package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NusIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NusId(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidNusId = "";
        assertThrows(IllegalArgumentException.class, () -> new NusId(invalidNusId));
    }

    @Test
    public void isValidNusId() {
        // null name
        assertThrows(NullPointerException.class, () -> NusId.isValidNusId(null));

        // invalid nusId
        assertFalse(NusId.isValidNusId("")); // empty string
        assertFalse(NusId.isValidNusId(" ")); // spaces only
        assertFalse(NusId.isValidNusId("^")); // only non-alphanumeric characters
        assertFalse(NusId.isValidNusId("peter*")); // contains non-alphanumeric characters
        assertFalse(NusId.isValidNusId("E")); // letters only
        assertFalse(NusId.isValidNusId("1234567")); // numbers only
        assertFalse(NusId.isValidNusId("e1234567")); // Lower case letter
        assertFalse(NusId.isValidNusId("e123456")); // Less digits
        assertFalse(NusId.isValidNusId("e12345678")); // More digits

        // valid nusId
        assertTrue(NusId.isValidNusId("E0951501")); // Correct format
        assertTrue(NusId.isValidNusId("E0000000")); // All 0
    }

    @Test
    public void equals() {
        NusId nusId = new NusId("E1234567");

        // same values -> returns true
        assertTrue(nusId.equals(new NusId("E1234567")));

        // same object -> returns true
        assertTrue(nusId.equals(nusId));

        // null -> returns false
        assertFalse(nusId.equals(null));

        // different types -> returns false
        assertFalse(nusId.equals(5.0f));

        // different values -> returns false
        assertFalse(nusId.equals(new Name("E7654321")));
    }
}
