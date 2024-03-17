package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Major(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Major.isValidAddress(null));

        // invalid addresses
        assertFalse(Major.isValidAddress("")); // empty string
        assertFalse(Major.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Major.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Major.isValidAddress("-")); // one character
        assertTrue(Major.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Major major = new Major("Valid Major");

        // same values -> returns true
        assertTrue(major.equals(new Major("Valid Major")));

        // same object -> returns true
        assertTrue(major.equals(major));

        // null -> returns false
        assertFalse(major.equals(null));

        // different types -> returns false
        assertFalse(major.equals(5.0f));

        // different values -> returns false
        assertFalse(major.equals(new Major("Other Valid Major")));
    }
}
