package seedu.address.model.house;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StreetTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Street(null));
    }

    @Test
    public void constructor_invalidBlock_throwsIllegalArgumentException() {
        String invalidStreet = "";
        assertThrows(IllegalArgumentException.class, () -> new Street(invalidStreet));
    }

    @Test
    public void isValidStreet() {
        // null street
        assertThrows(NullPointerException.class, () -> Street.isValidStreet(null));

        // invalid street
        assertFalse(Street.isValidStreet("")); // empty string
        assertFalse(Street.isValidStreet(" ")); // spaces only
        assertFalse(Street.isValidStreet("-")); // hyphen only
        assertFalse(Street.isValidStreet("@!^&*%&^")); // non-alphanumeric
        assertFalse(Street.isValidStreet("qwe!@#ert$%")); // non-alphanumeric + alphabets
        assertFalse(Street.isValidStreet("!@#123")); // non-alphanumeric + numbers
        assertFalse(Street.isValidStreet("t3$t!ng")); // non-alphanumeric + alphanumerics

        // valid street
        assertTrue(Street.isValidStreet("292A East Coast Rd"));
        assertTrue(Street.isValidStreet("350 Orchard Rd Sshaw House 13-01")); // with dash
        assertTrue(Street.isValidStreet("46 PANDAN LOOP BLK 3 PANDAN LIGHT IND PARK")); // all caps
    }

    @Test
    public void equals() {
        Street street = new Street("15 Kaki Bukit View 01-00");

        // same values -> returns true
        assertTrue(street.equals(new Street("15 Kaki Bukit View 01-00")));

        // same object -> returns true
        assertTrue(street.equals(street));

        // null -> returns false
        assertFalse(street.equals(null));

        // different types -> returns false
        assertFalse(street.equals(5.0f));

        // different values -> returns false
        assertFalse(street.equals(new Street("3 Sungei Kadut Street 6 Sungei Kadut Industrial Estate")));
    }
}
