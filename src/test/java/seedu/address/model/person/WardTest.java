package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WardTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ward(null));
    }

    @Test
    public void constructor_invalidWard_throwsIllegalArgumentException() {
        String invalidWard = "";
        assertThrows(IllegalArgumentException.class, () -> new Ward(invalidWard));
    }

    @Test
    public void isValidWard() {
        // null Ward
        assertThrows(NullPointerException.class, () -> Ward.isValidWard(null));

        // blank Ward
        assertFalse(Ward.isValidWard("")); // empty string
        assertFalse(Ward.isValidWard(" ")); // spaces only

        // invalid ward address
        assertFalse(Ward.isValidWard("A_1234_X")); // underscore
        assertFalse(Ward.isValidWard("A.1234X")); // period
        assertFalse(Ward.isValidWard("A1234X+")); // '+' symbol
        assertFalse(Ward.isValidWard("A-1234X")); // hyphen
        assertFalse(Ward.isValidWard("a1+be.d")); // mixture of alphanumeric and special characters

        // valid Ward
        assertTrue(Ward.isValidWard("A1234X"));
        assertTrue(Ward.isValidWard("abcd")); // alphabets only
        assertTrue(Ward.isValidWard("1233")); // numeric only
    }

    @Test
    public void equals() {
        Ward ward = new Ward("A1234X");

        // same values -> returns true
        assertTrue(ward.equals(new Ward("A1234X")));

        // same object -> returns true
        assertTrue(ward.equals(ward));

        // null -> returns false
        assertFalse(ward.equals(null));

        // different types -> returns false
        assertFalse(ward.equals(5.0f));

        // different values -> returns false
        assertFalse(ward.equals(new Ward("B5678D")));
    }
}
