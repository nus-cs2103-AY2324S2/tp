package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FamilyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Family(null));
    }

    @Test
    public void constructor_invalidFamily_throwsNumberFormatException() {
        String invalidFamily = "";
        assertThrows(NumberFormatException.class, () -> new Family(invalidFamily));
    }

    @Test
    public void constructor_invalidFamily_throwsNumberFormatException2() {
        String invalidFamily = " ";
        assertThrows(NumberFormatException.class, () -> new Family(invalidFamily));
    }

    @Test
    public void constructor_invalidFamily_throwsNumberFormatException3() {
        String invalidFamily = " family"; //alphabets
        assertThrows(NumberFormatException.class, () -> new Family(invalidFamily));
    }

    @Test
    public void constructor_invalidFamily_throwsNumberFormatException4() {
        String invalidFamily = "9011p041"; //alphabet within digits
        assertThrows(NumberFormatException.class, () -> new Family(invalidFamily));
    }

    @Test
    public void constructor_invalidFamily_throwsNumberFormatException5() {
        String invalidFamily = "9312 1534"; // spaces within digits
        assertThrows(NumberFormatException.class, () -> new Family(invalidFamily));
    }

    @Test
    public void isValidFamily() {
        // null family size
        assertThrows(NumberFormatException.class, () -> Family.isValidFamily(null));

        // invalid family sizes
        assertFalse(Family.isValidFamily("0")); // less than 1

        // valid family numbers
        assertTrue(Family.isValidFamily("2")); // more than 1
        assertTrue(Family.isValidFamily("93121"));
        assertTrue(Family.isValidFamily("1242938")); // long number
    }

    @Test
    public void equals() {
        Family family = new Family("999");

        // same values -> returns true
        assertTrue(family.equals(new Family("999")));

        // same object -> returns true
        assertTrue(family.equals(family));

        // null -> returns false
        assertFalse(family.equals(null));

        // different types -> returns false
        assertFalse(family.equals(5.0f));

        // different values -> returns false
        assertFalse(family.equals(new Family("995")));
    }
}
