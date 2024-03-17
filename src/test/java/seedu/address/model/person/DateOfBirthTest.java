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
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDateOfBirth("13122023")); // numbers only
        assertFalse(DateOfBirth.isValidDateOfBirth("13/122023")); // one slash
        assertFalse(DateOfBirth.isValidDateOfBirth("1312/2023")); // one slash
        assertFalse(DateOfBirth.isValidDateOfBirth("1/3/2023")); // shortened date and month
        assertFalse(DateOfBirth.isValidDateOfBirth("2023/12/13")); // reverse format

        // valid addresses
        assertTrue(DateOfBirth.isValidDateOfBirth("13/12/2023"));
    }

    @Test
    public void equals() {
        DateOfBirth date = new DateOfBirth("13/12/2020");

        // same values -> returns true
        assertTrue(date.equals(new DateOfBirth("13/12/2020")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new DateOfBirth("13/12/2023")));
    }
}