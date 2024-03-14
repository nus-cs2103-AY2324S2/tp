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
    public void constructor_invalidCountry_throwsIllegalArgumentException() {
        String invalidCountry = "";
        assertThrows(IllegalArgumentException.class, () -> new Country(invalidCountry));
    }

    @Test
    public void isValidCountry() {
        // null address
        assertThrows(NullPointerException.class, () -> Country.isValidCountry(null));

        // invalid addresses
        assertFalse(Country.isValidCountry("")); // empty string
        assertFalse(Country.isValidCountry(" ")); // spaces only

        // valid addresses
        assertTrue(Country.isValidCountry("Blk 456, Den Road, #01-355"));
        assertTrue(Country.isValidCountry("-")); // one character
        assertTrue(Country.isValidCountry("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
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
