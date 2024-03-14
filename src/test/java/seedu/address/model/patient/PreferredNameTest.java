package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PreferredNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PreferredName(null));
    }

    @Test
    public void constructor_invalidPreferredName_throwsIllegalArgumentException() {
        String invalidPreferredName = "";
        assertThrows(IllegalArgumentException.class, () -> new PreferredName(invalidPreferredName));
    }

    @Test
    public void isValidPreferredName() {
        // null address
        assertThrows(NullPointerException.class, () -> PreferredName.isValidPreferredName(null));

        // invalid preferred name
        assertFalse(PreferredName.isValidPreferredName("")); // empty string
        assertFalse(PreferredName.isValidPreferredName(" ")); // spaces only

        // valid preferred name
        assertTrue(PreferredName.isValidPreferredName("Kenny"));
        assertTrue(PreferredName.isValidPreferredName("-")); // one character
        assertTrue(PreferredName.isValidPreferredName("Ah Ken")); // with spaces
    }

    @Test
    public void equals() {
        PreferredName preferredName = new PreferredName("Valid Preferred Name");

        // same values -> returns true
        assertTrue(preferredName.equals(new PreferredName("Valid Preferred Name")));

        // same object -> returns true
        assertTrue(preferredName.equals(preferredName));

        // null -> returns false
        assertFalse(preferredName.equals(null));

        // different types -> returns false
        assertFalse(preferredName.equals(5.0f));

        // different values -> returns false
        assertFalse(preferredName.equals(new PreferredName("Other Valid Preferred Name")));
    }
}
