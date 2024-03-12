package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmploymentTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EmploymentType(null));
    }

    @Test
    public void constructor_invalidEmploymentType_throwsIllegalArgumentException() {
        String invalidEmploymentType = "";
        assertThrows(IllegalArgumentException.class, () -> new EmploymentType(invalidEmploymentType));
    }

    @Test
    public void isValidEmploymentType() {
        // null EmploymentType number
        assertThrows(NullPointerException.class, () -> EmploymentType.isValidEmploymentType(null));

        // invalid EmploymentType numbers
        assertFalse(EmploymentType.isValidEmploymentType("")); // empty string
        assertFalse(EmploymentType.isValidEmploymentType(" ")); // spaces only
        assertFalse(EmploymentType.isValidEmploymentType("mt"));
        assertFalse(EmploymentType.isValidEmploymentType("3")); // numeric

        // valid EmploymentType numbers
        assertTrue(EmploymentType.isValidEmploymentType("pt"));
        assertTrue(EmploymentType.isValidEmploymentType("ft"));
    }

    @Test
    public void equals() {
        EmploymentType employmentType = new EmploymentType("ft");

        // same values -> returns true
        assertTrue(employmentType.equals(new EmploymentType("ft")));

        // same object -> returns true
        assertTrue(employmentType.equals(employmentType));

        // null -> returns false
        assertFalse(employmentType.equals(null));

        // different types -> returns false
        assertFalse(employmentType.equals(5.0f));

        // different values -> returns false
        assertFalse(employmentType.equals(new EmploymentType("pt")));
    }
}
