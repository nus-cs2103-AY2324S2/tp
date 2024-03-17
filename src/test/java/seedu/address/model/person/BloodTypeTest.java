package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null, null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidType = "";
        String invalidRh = "";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidType, invalidRh));
    }

    @Test
    public void equals() {
        BloodType bloodType = new BloodType("A", "POSITIVE");

        // same values -> returns true
        assertTrue(bloodType.equals(new BloodType("A", "POSITIVE")));

        // same object -> returns true
        assertTrue(bloodType.equals(bloodType));

        // null -> returns false
        assertFalse(bloodType.equals(null));

        // different types -> returns false
        assertFalse(bloodType.equals(5.0f));

        // different letters -> returns false
        assertFalse(bloodType.equals(new BloodType("B", "POSITIVE")));

        // different letters -> returns false
        assertFalse(bloodType.equals(new BloodType("A", "NEGATIVE")));
    }
}