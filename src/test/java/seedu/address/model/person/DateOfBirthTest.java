package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDate));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> DateOfAdmission.isValidDateOfAdmission(null));

        // invalid addresses
        assertFalse(DateOfAdmission.isValidDateOfAdmission("")); // empty string
        assertFalse(DateOfAdmission.isValidDateOfAdmission(" ")); // spaces only
        assertFalse(DateOfAdmission.isValidDateOfAdmission("20231213")); // numbers only
        assertFalse(DateOfAdmission.isValidDateOfAdmission("2023-1213")); // one slash
        assertFalse(DateOfAdmission.isValidDateOfAdmission("202312-13")); // one slash
        assertFalse(DateOfAdmission.isValidDateOfAdmission("2023-1-3")); // shortened date and month
        assertFalse(DateOfAdmission.isValidDateOfAdmission("13-12-2023")); // reverse format

        // valid addresses
        assertTrue(DateOfAdmission.isValidDateOfAdmission("2023-12-13"));
    }

    @Test
    public void equals() {
        DateOfBirth date = new DateOfBirth("2020-12-13");

        // same values -> returns true
        assertTrue(date.equals(new DateOfBirth("2020-12-13")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new DateOfBirth("2023-12-13")));
    }
}
