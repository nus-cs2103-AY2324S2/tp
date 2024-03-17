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
    public void constuctor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    public void isValidDateOfBirth() {
        // null date of birth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // invalid date of birth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only
        assertFalse(DateOfBirth.isValidDateOfBirth("wdalsjdaj")); // random string
        assertFalse(DateOfBirth.isValidDateOfBirth("12/2024")); // without date
        assertFalse(DateOfBirth.isValidDateOfBirth("245/2/2024")); // invalid date
        assertFalse(DateOfBirth.isValidDateOfBirth("25/24/2024")); // invalid month
        assertFalse(DateOfBirth.isValidDateOfBirth("25/2/22")); // invalid year

        // valid date of birth
        assertTrue(DateOfBirth.isValidDateOfBirth("25/2/2024")); // valid first input foramt
        assertTrue(DateOfBirth.isValidDateOfBirth("2024-2-24")); // valid second input format
    }
}
