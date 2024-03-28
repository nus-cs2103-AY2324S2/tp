package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SexTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sex(null));
    }

    @Test
    public void constructor_invalidSex_throwsIllegalArgumentException() {
        String invalidSex = "";
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidSex));
    }

    @Test
    public void isValidSex() {
        // null sex
        assertThrows(NullPointerException.class, () -> Sex.isValidSex(null));

        // invalid sex
        assertFalse(Sex.isValidSex("")); // empty string
        assertFalse(Sex.isValidSex(" ")); // spaces only
        assertFalse(Sex.isValidSex("^")); // only non-alphanumeric characters
        assertFalse(Sex.isValidSex("peter*")); // contains non-alphanumeric characters

        // valid sex
        assertTrue(Sex.isValidSex("M")); // one character
        assertTrue(Sex.isValidSex("F")); // one character
    }

    @Test
    public void equals() {
        Sex sex = new Sex("M");

        // same values -> returns true
        assertEquals(sex, new Sex("M"));

        // same object -> returns true
        assertEquals(sex, sex);

        // null -> returns false
        assertNotEquals(null, sex);

        // different types -> returns false
        assertFalse(sex.equals(5.0f));

        // different values -> returns false
        assertFalse(sex.equals(new Sex("F")));
    }

    @Test
    public void hash() {
        Sex sexMale = new Sex("M");
        Sex sexMaleCopy = new Sex("M");
        Sex sexFemale = new Sex("F");

        // same values -> returns true
        assertEquals(sexMale.hashCode(), sexMaleCopy.hashCode());

        // different values -> returns false
        assertNotEquals(sexMale.hashCode(), sexFemale.hashCode());
    }
}
