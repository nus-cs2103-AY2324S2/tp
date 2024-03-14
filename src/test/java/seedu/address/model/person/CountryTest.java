package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Country(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Country(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Country.isValidAddress(null));

        // invalid addresses
        assertFalse(Country.isValidAddress("")); // empty string
        assertFalse(Country.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Country.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Country.isValidAddress("-")); // one character
        assertTrue(Country.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        Country country = new Country("Valid Country");

        // same values -> returns true
        assertTrue(country.equals(new Country("Valid Country")));

        // same object -> returns true
        assertTrue(country.equals(country));

        // null -> returns false
        assertFalse(country.equals(null));

        // different types -> returns false
        assertFalse(country.equals(5.0f));

        // different values -> returns false
        assertFalse(country.equals(new Country("Other Valid Country")));
    }
}
