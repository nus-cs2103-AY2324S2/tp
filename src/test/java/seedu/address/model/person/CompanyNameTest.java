package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidName(null));

        // invalid name
        assertFalse(CompanyName.isValidName("")); // empty string
        assertFalse(CompanyName.isValidName(" ")); // spaces only
        assertFalse(CompanyName.isValidName("123456789 123456789 123456789 123456789 123456789 123456789 123456789 "
                + "123456789 123456789 123456789 1")); // contains more than 100 chracters


        // valid name
        assertTrue(CompanyName.isValidName("Google")); // alphabets only
        assertTrue(CompanyName.isValidName("711")); // numbers only
        assertTrue(CompanyName.isValidName("Capital Land")); // with capital letters
        assertTrue(CompanyName.isValidName("S&P 500")); // with capital letters and non-alphanumeric characters
        assertTrue(CompanyName.isValidName("Essilor International Compagnie Generale d'Optique SA")); // long names
    }

    @Test
    public void equals() {
        CompanyName name = new CompanyName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new CompanyName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new CompanyName("Other Valid Name")));
    }
}
