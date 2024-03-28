package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmploymentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidEmployment_throwsIllegalArgumentException() {
        String invalidEmployment = "";
        assertThrows(IllegalArgumentException.class, () -> new Employment(invalidEmployment));
    }

    @Test
    public void isValidEmployment() {
        // null Employment
        assertThrows(NullPointerException.class, () -> Employment.isValidEmployment(null));

        // invalid Employment
        assertFalse(Employment.isValidEmployment("")); // empty string
        assertFalse(Employment.isValidEmployment(" ")); // spaces only
        assertFalse(Employment.isValidEmployment("^")); // only non-alphanumeric characters
        assertFalse(Employment.isValidEmployment("peter*")); // contains non-alphanumeric characters
        assertFalse(Employment.isValidEmployment("other"));

        // valid Employment
        assertTrue(Employment.isValidEmployment("part-time"));
        assertTrue(Employment.isValidEmployment("full-time"));
    }

    @Test
    public void equals() {
        Employment employment = new Employment("part-time");

        // same values -> returns true
        assertTrue(employment.equals(new Employment("part-time")));

        // same object -> returns true
        assertTrue(employment.equals(employment));

        // null -> returns false
        assertFalse(employment.equals(null));

        // different types -> returns false
        assertFalse(employment.equals("part-time"));

        // different values -> returns false
        assertFalse(employment.equals(new Employment("full-time")));
    }
}
