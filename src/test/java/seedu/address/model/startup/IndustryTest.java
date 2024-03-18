package seedu.address.model.startup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IndustryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Industry(null));
    }

    @Test
    public void constructor_invalidIndustry_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Industry(invalidName));
    }

    @Test
    public void isValidIndustry() {
        assertThrows(NullPointerException.class, () -> Industry.isValidIndustry(null));
        assertFalse(Industry.isValidIndustry("")); // empty string
        assertFalse(Industry.isValidIndustry(" ")); // spaces only
        assertFalse(Industry.isValidIndustry("^")); // only non-alphanumeric characters
        assertFalse(Industry.isValidIndustry("finance*")); // contains non-alphanumeric characters
        assertTrue(Industry.isValidIndustry("Finance")); // alphabets only
        assertTrue(Industry.isValidIndustry("MANUFACTURING")); // all capital
        assertTrue(Industry.isValidIndustry("web 3")); // with numbers
    }

    @Test
    public void equals() {
        Industry industry = new Industry("Valid Industry");

        // same values -> returns true
        assertTrue(industry.equals(new Industry("Valid Industry")));

        // same object -> returns true
        assertTrue(industry.equals(industry));

        // null -> returns false
        assertFalse(industry.equals(null));

        // different types -> returns false
        assertFalse(industry.equals(5.0f));

        // different values -> returns false
        assertFalse(industry.equals(new Industry("Other Valid Industry")));
    }
}
