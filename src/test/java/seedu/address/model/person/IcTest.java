package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ic(null));
    }

    @Test
    public void constructor_invalidIc_throwsIllegalArgumentException() {
        String invalidIc = "";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidIc));
    }

    @Test
    public void isValidIc() {
        // null Ic
        assertThrows(NullPointerException.class, () -> Ic.isValidIc(null));

        // blank Ic
        assertFalse(Ic.isValidIc("")); // empty string
        assertFalse(Ic.isValidIc(" ")); // spaces only

        // invalid parts
        assertFalse(Ic.isValidIc("A_12345B")); // underscore
        assertFalse(Ic.isValidIc("A 12345 B")); // spaces
        assertFalse(Ic.isValidIc(" A12345B")); // leading space
        assertFalse(Ic.isValidIc("A12345B ")); // trailing space
        assertFalse(Ic.isValidIc("@12345B")); // '@'
        assertFalse(Ic.isValidIc("-A12345B")); // starts with a hyphen
        assertFalse(Ic.isValidIc("A12345-")); // ends with a hyphen
        assertFalse(Ic.isValidIc("A.12345B")); // periods

        // valid Ic
        assertTrue(Ic.isValidIc("A12345B"));
        assertTrue(Ic.isValidIc("ABCD")); // alphabets only
        assertTrue(Ic.isValidIc("12345")); // numeric only
    }

    @Test
    public void equals() {
        Ic Ic = new Ic("A12345B");

        // same values -> returns true
        assertTrue(Ic.equals(new Ic("A12345B")));

        // same object -> returns true
        assertTrue(Ic.equals(Ic));

        // null -> returns false
        assertFalse(Ic.equals(null));

        // different types -> returns false
        assertFalse(Ic.equals(5));

        // different values -> returns false
        assertFalse(Ic.equals(new Ic("W5678X")));
    }
}
