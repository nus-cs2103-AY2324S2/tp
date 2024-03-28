package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdentityCardNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IdentityCardNumber(null));
    }

    @Test
    public void constructor_invalidIdentityCardNumber_throwsIllegalArgumentException() {
        String invalidIdentityCardNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new IdentityCardNumber(invalidIdentityCardNumber));
    }

    @Test
    public void isValidIdentityCardNumber() {
        // null identity card number
        assertThrows(NullPointerException.class, () -> IdentityCardNumber.isValidIdentityCardNumber(null));

        // invalid identity card number
        assertFalse(IdentityCardNumber.isValidIdentityCardNumber("")); // empty string
        assertFalse(IdentityCardNumber.isValidIdentityCardNumber(" ")); // spaces only
        assertFalse(IdentityCardNumber.isValidIdentityCardNumber("1234abc")); // alphanumeric
        assertFalse(IdentityCardNumber.isValidIdentityCardNumber("1234 5678")); // spaces within digits

        // valid identity card number
        assertTrue(IdentityCardNumber.isValidIdentityCardNumber("S1234567A")); // starts with S
        assertTrue(IdentityCardNumber.isValidIdentityCardNumber("T1234567A")); // starts with T
        assertTrue(IdentityCardNumber.isValidIdentityCardNumber("F1234567A")); // starts with F
        assertTrue(IdentityCardNumber.isValidIdentityCardNumber("G1234567A")); // starts with G

    }

    @Test
    public void equals() {
        // remember the format is [STFG][0-9]{7}[A-Z]
        IdentityCardNumber identityCardNumber = new IdentityCardNumber("S1234567A");

        // same values -> returns true
        IdentityCardNumber identityCardNumberCopy = new IdentityCardNumber("S1234567A");

        // same object -> returns true
        assertTrue(identityCardNumber.equals(identityCardNumber));

        // null -> returns false
        assertFalse(identityCardNumber.equals(null));

        // different types -> returns false
        assertFalse(identityCardNumber.equals(5.0f));

        // different values -> returns false
        assertFalse(identityCardNumber.equals(new IdentityCardNumber("T1234567A")));
    }

    @Test
    public void hash() {
        // same object -> returns true
        IdentityCardNumber identityCardNumber = new IdentityCardNumber("S1234567A");
        assertEquals(identityCardNumber.hashCode(), identityCardNumber.hashCode());

        // same values -> returns true
        IdentityCardNumber identityCardNumberCopy = new IdentityCardNumber("S1234567A");
        assertEquals(identityCardNumber.hashCode(), identityCardNumberCopy.hashCode());

        // different values -> returns false
        assertNotEquals(identityCardNumber.hashCode(), new IdentityCardNumber("T1234567A").hashCode());
    }
}
