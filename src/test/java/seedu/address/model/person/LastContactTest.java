package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastContactTest {

    @Test
    void constructor_nullDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastContact(null));
    }

    @Test
    void constructor_EmptyDateTime_throwsNullPointerException() {
        String validDateTime = "";
        LastContact lastContact = new LastContact(validDateTime);
        assertEquals(validDateTime, lastContact.toString());
        assertNull(lastContact.getDateTime());
    }

    @Test
    void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "32-12-2020 2500"; // This date and time are invalid
        assertThrows(IllegalArgumentException.class, () -> new LastContact(invalidDateTime));
    }

    @Test
    void constructor_validDateTime_createsLastContact() {
        String validDateTime = "05-03-2024 0600";
        LastContact lastContact = new LastContact(validDateTime);
        assertEquals(validDateTime, lastContact.toString());
    }

    @Test
    void isValidDateTime() {
        // Invalid date formats
        assertFalse(LastContact.isValidDateTime("31-02-2020 2400")); // Invalid date and time
        assertFalse(LastContact.isValidDateTime("05-13-2024 0600")); // Invalid month
        assertTrue(LastContact.isValidDateTime("")); // Empty string

        // Valid date format
        assertTrue(LastContact.isValidDateTime("05-03-2024 0600")); // Valid date and time
    }

    @Test
    void compareTo() {
        LastContact lastcontact1 = new LastContact("05-12-2024 0600");
        LastContact lastcontact2 = new LastContact("05-12-2024 0600");
        LastContact lastcontact3 = new LastContact("05-12-2024 0500");
        assertEquals(0, lastcontact1.compareTo(lastcontact2));
        assertEquals(1, lastcontact1.compareTo(lastcontact3));
    }

    @Test
    void getDateTimeString() {
        String validDateTime = "05-03-2024 0600";
        LastContact lastContact = new LastContact(validDateTime);
        assertEquals(validDateTime, lastContact.toString());
    }

    @Test
    void equals() {
        LastContact lastContact1 = new LastContact("05-03-2024 0600");
        LastContact lastContact2 = new LastContact("05-03-2024 0600");
        LastContact lastContact3 = new LastContact("06-03-2024 0600");

        assertFalse(lastContact1.equals(null)); // test null return false
        assertEquals(lastContact1, lastContact2); // Same date and time
        assertFalse(lastContact1.equals(lastContact3)); // Different date and time
    }

    @Test
    void hashCodeTest() {
        LastContact lastContact1 = new LastContact("05-03-2024 0600");
        LastContact lastContact2 = new LastContact("05-03-2024 0600");

        assertEquals(lastContact1.hashCode(), lastContact2.hashCode()); // Same hash code for same date and time
    }
}
