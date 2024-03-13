package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsDateTimeParseException() {
        String invalidDob = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDob));
    }

    @Test
    public void isValidDateOfBirth() {
        // null dob
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // invalid dob
        assertFalse(DateOfBirth.isValidDateOfBirth(LocalDate.now().plusDays(1).toString())); // Tomorrow

        // Test dob
        assertTrue(DateOfBirth.isValidDateOfBirth(LocalDate.now().minusDays(1).toString())); // Yesterday
    }

    @Test
    public void equals() {
        DateOfBirth dob = new DateOfBirth("2001-01-01");

        // same values -> returns true
        assertTrue(dob.equals(new DateOfBirth("2001-01-01")));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // null -> returns false
        assertFalse(dob.equals(null));

        // different types -> returns false
        assertFalse(dob.equals(5.0f));

        // different values -> returns false
        assertFalse(dob.equals(new DateOfBirth("2001-01-02")));
    }
}
