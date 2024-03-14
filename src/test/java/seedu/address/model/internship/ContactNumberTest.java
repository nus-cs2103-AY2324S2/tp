package seedu.address.model.internship;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ContactNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactNumber(null));
    }

    @Test
    public void constructor_invalidContactNumber_throwsIllegalArgumentException() {
        String invalidContactNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactNumber(invalidContactNumber));
    }

    @Test
    public void isValidContactNumber() {
        // null contactNumber number
        assertThrows(NullPointerException.class, () -> ContactNumber.isValidContactNumber(null));

        // invalid contactNumber numbers
        assertFalse(ContactNumber.isValidContactNumber("")); // empty string
        assertFalse(ContactNumber.isValidContactNumber(" ")); // spaces only
        assertFalse(ContactNumber.isValidContactNumber("91")); // less than 3 numbers
        assertFalse(ContactNumber.isValidContactNumber("contactNumber")); // non-numeric
        assertFalse(ContactNumber.isValidContactNumber("9011p041")); // alphabets within digits
        assertFalse(ContactNumber.isValidContactNumber("9312 1534")); // spaces within digits

        // valid contactNumber numbers
        assertTrue(ContactNumber.isValidContactNumber("911")); // exactly 3 numbers
        assertTrue(ContactNumber.isValidContactNumber("93121534"));
        assertTrue(ContactNumber.isValidContactNumber("124293842033123")); // long contactNumber numbers
    }

    @Test
    public void equals() {
        ContactNumber contactNumber = new ContactNumber("999");

        // same values -> returns true
        assertTrue(contactNumber.equals(new ContactNumber("999")));

        // same object -> returns true
        assertTrue(contactNumber.equals(contactNumber));

        // null -> returns false
        assertFalse(contactNumber.equals(null));

        // different types -> returns false
        assertFalse(contactNumber.equals(5.0f));

        // different values -> returns false
        assertFalse(contactNumber.equals(new ContactNumber("995")));
    }}
