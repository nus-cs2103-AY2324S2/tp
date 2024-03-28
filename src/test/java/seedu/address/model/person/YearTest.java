package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class YearTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Year(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Year(invalidPhone));
    }

    @Test
    public void isValidYear() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Year.isValidYear(null));

        // invalid years
        assertFalse(Year.isValidYear("")); // empty string
        assertFalse(Year.isValidYear(" ")); // spaces only
        assertFalse(Year.isValidYear("7")); // less than 3 numbers
        assertFalse(Year.isValidYear("year")); // non-numeric
        assertFalse(Year.isValidYear("a1")); // alphabets with digit

        // valid years
        assertTrue(Year.isValidYear("1")); // exactly year 1
        assertTrue(Year.isValidYear("2")); // exactly year 2
        assertTrue(Year.isValidYear("3")); // exactly year 3
        assertTrue(Year.isValidYear("4")); // exactly year 4
        assertTrue(Year.isValidYear("5")); // exactly year 5
    }

    @Test
    public void equals() {
        Year year = new Year("1");

        // same values -> returns true
        assertTrue(year.equals(new Year("1")));

        // same object -> returns true
        assertTrue(year.equals(year));

        // null -> returns false
        assertFalse(year.equals(null));

        // different types -> returns false
        assertFalse(year.equals(5.0f));

        // different values -> returns false
        assertFalse(year.equals(new Year("2")));
    }
}
