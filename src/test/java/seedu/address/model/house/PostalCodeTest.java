package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PostalCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PostalCode(null));
    }

    @Test
    public void constructor_invalidPostalCode_throwsIllegalArgumentException() {
        String invalidPostalCode = "12345"; // Not 6 digits
        assertThrows(IllegalArgumentException.class, () -> new PostalCode(invalidPostalCode));
    }

    @Test
    public void isValidPostalCode() {
        // null postal code
        assertThrows(NullPointerException.class, () -> PostalCode.isValidPostalCode(null));

        // invalid postal codes
        assertFalse(PostalCode.isValidPostalCode("")); // empty string
        assertFalse(PostalCode.isValidPostalCode(" ")); // spaces only
        assertFalse(PostalCode.isValidPostalCode("12345")); // less than 6 numbers
        assertFalse(PostalCode.isValidPostalCode("postal")); // non-numeric
        assertFalse(PostalCode.isValidPostalCode("1234p6")); // alphabets within digits
        assertFalse(PostalCode.isValidPostalCode("123 456")); // spaces within digits

        // valid postal codes
        assertTrue(PostalCode.isValidPostalCode("123456")); // exactly 6 numbers
    }

    @Test
    public void equals() {
        PostalCode postalCode = new PostalCode("654321");

        // same values -> returns true
        assertTrue(postalCode.equals(new PostalCode("654321")));

        // same object -> returns true
        assertTrue(postalCode.equals(postalCode));

        // null -> returns false
        assertFalse(postalCode.equals(null));

        // different types -> returns false
        assertFalse(postalCode.equals(5.0f));

        // different values -> returns false
        assertFalse(postalCode.equals(new PostalCode("123456")));
    }
}
