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
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // blank nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only

        // missing parts
        assertFalse(Nric.isValidNric("01234567A")); // missing first alphabet
        assertFalse(Nric.isValidNric("T01234567")); // missing last alphabet

        // invalid parts
        assertFalse(Nric.isValidNric("T01234567@")); // "@' invalid
        assertFalse(Nric.isValidNric("@0123456A")); // "@' invalid
        assertFalse(Nric.isValidNric("T012345A")); // missing number
        assertFalse(Nric.isValidNric("T01234567A")); // extra number
        assertFalse(Nric.isValidNric("TT0123456A")); // extra first alphabet
        assertFalse(Nric.isValidNric("T0123456AA")); // extra last alphabet

        // valid nric
        assertTrue(Nric.isValidNric("T0123456A"));
    }

    @Test
    public void equals() {
        Nric nric = new Nric("T0123456A");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("T0123456A")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T0123456B")));
    }
}
