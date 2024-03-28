package seedu.address.model.internship;

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
    public void constructor_invalidCompanyName_throwsIllegalArgumentException() {
        String invalidCompanyName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidCompanyName));
    }

    @Test
    public void isValidCompanyName() {
        // null companyNames
        assertThrows(NullPointerException.class, () -> CompanyName.isValidCompanyName(null));

        // invalid companyNames
        assertFalse(CompanyName.isValidCompanyName(""));
        assertFalse(CompanyName.isValidCompanyName(" "));
        assertFalse(CompanyName.isValidCompanyName("^"));
        assertFalse(CompanyName.isValidCompanyName("Facebook*"));
        assertFalse(CompanyName.isValidCompanyName(" Microsoft")); // leading space


        // valid companyNames
        assertTrue(CompanyName.isValidCompanyName("Microsoft"));
        assertTrue(CompanyName.isValidCompanyName("Hewlett Packard"));
        assertTrue(CompanyName.isValidCompanyName("Kentucky Fried Chicken"));
    }

    @Test
    public void equals() {
        CompanyName companyName = new CompanyName("Google");

        // same values -> returns true
        assertTrue(companyName.equals(new CompanyName("Google")));

        // same object -> returns true
        assertTrue(companyName.equals(companyName));

        // null -> returns false
        assertFalse(companyName.equals(null));

        // different types -> returns false
        assertFalse(companyName.equals(5.0f));

        // different values -> returns false
        assertFalse(companyName.equals(new CompanyName("Microsoft")));
    }
}
