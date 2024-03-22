package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BloodType(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidBloodType = "ABB-";
        assertThrows(IllegalArgumentException.class, () -> new BloodType(invalidBloodType));
    }

    @Test
    public void equals() {
        BloodType bloodType = new BloodType("A+");

        // same values -> returns true
        assertEquals(bloodType, new BloodType("A+"));

        // same object -> returns true
        assertEquals(bloodType, bloodType);

        // null -> returns false
        assertNotEquals(null, bloodType);

        // different types -> returns false
        assertNotEquals(5.0f, bloodType);

        // different rh type -> returns false
        assertNotEquals(bloodType, new BloodType("A-"));

        // different letter type -> returns false
        assertFalse(bloodType.equals(new BloodType("B+")));
    }
}
