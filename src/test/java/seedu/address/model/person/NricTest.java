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
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid addresses
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("0312345")); // numbers only
        assertFalse(Nric.isValidNric("S0312345")); // without first letter
        assertFalse(Nric.isValidNric("0312345A")); // without last letter
        assertFalse(Nric.isValidNric("T03123425A")); // too many numbers
        assertFalse(Nric.isValidNric("T031234A")); // too few numbers
        assertFalse(Nric.isValidNric("D03123452S")); // invalid first number

        // valid addresses
        assertTrue(Nric.isValidNric("T0912345A"));
    }

    @Test
    public void equals() {
        Nric nric = new Nric("T0412345G");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("T0412345G")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T0412345H")));
    }
}