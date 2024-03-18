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
    public void constructor_invalidFamily_throwsIllegalArgumentException() {
        String invalidFamily = "";
        assertThrows(IllegalArgumentException.class, () -> new Family(invalidFamily));

        String invalidFamilyWithSpaces = " ";
        assertThrows(IllegalArgumentException.class, () -> new Family(invalidFamilyWithSpaces));
    }

    @Test
    public void isValidFamily() {
        // null family size
        assertThrows(NullPointerException.class, () -> Family.isValidFamily(null));

        // invalid family sizes
        assertFalse(Family.isValidFamily("0")); // less than 1
        assertFalse(Family.isValidFamily("-1")); // less than 1
        assertFalse(Family.isValidFamily("-2")); // less than 1
        assertFalse(Family.isValidFamily("-12313231231313")); // very negative

        // valid family numbers
        assertTrue(Family.isValidFamily("2")); // more than 1
        assertTrue(Family.isValidFamily("93121")); // huge family
        assertTrue(Family.isValidFamily("12422131313131938")); // very huge family
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

        // different types (integer vs float) -> returns false
        assertFalse(family.equals(5.0f));

        //different types (integer vs words) -> returns false
        assertFalse(family.equals("OKKK"));

        // different values -> returns false
        assertFalse(family.equals(new Family("995")));
    }
}
