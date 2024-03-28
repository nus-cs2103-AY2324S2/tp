package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "A0234";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null name
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid name
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("^")); // only non-alphanumeric characters
        assertFalse(Nric.isValidNric("S0923*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Nric.isValidNric("G1234567A")); // alphabets only
        assertTrue(Nric.isValidNric("S0947384B")); // numbers only
        assertTrue(Nric.isValidNric("T8749283C")); // alphanumeric characters
        assertTrue(Nric.isValidNric("g8947283d")); // with capital letters
        assertTrue(Nric.isValidNric("m8749508e")); // long names
    }

    @Test
    public void equals() {
        Nric nric = new Nric("S1234567A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("S1234567A")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("S0123456A")));
    }
}
