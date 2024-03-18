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
        assertFalse(Country.isValidCountry("USA")); // not an ISO-3166-1 alpha-2 code

        // valid addresses
        assertTrue(Country.isValidCountry("SG"));
        assertTrue(Country.isValidCountry("JP"));
        assertTrue(Country.isValidCountry("US"));
    }

    @Test
    public void equals() {
        Country country = new Country("SG");

        // same values -> returns true
        assertTrue(country.equals(new Country("SG")));

        // same object -> returns true
        assertTrue(country.equals(country));

        // null -> returns false
        assertFalse(country.equals(null));

        // different types -> returns false
        assertFalse(country.equals(5.0f));

        // different values -> returns false
        assertFalse(country.equals(new Country("US")));
    }
}
