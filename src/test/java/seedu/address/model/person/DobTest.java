package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DobTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Dob(null));
    }

    @Test
    public void constructor_invalidDob_throwsIllegalArgumentException() {
        String invalidDob = "";
        assertThrows(IllegalArgumentException.class, () -> new Dob(invalidDob));
    }

    @Test
    public void isValidDob() {
        // null dob
        assertThrows(NullPointerException.class, () -> Dob.isValidDob(null));

        // blank dob
        assertFalse(Dob.isValidDob("")); // empty string
        assertFalse(Dob.isValidDob(" ")); // spaces only

        // missing parts
        assertFalse(Dob.isValidDob("17/03/")); // missing year
        assertFalse(Dob.isValidDob("/03/2024")); // missing day
        assertFalse(Dob.isValidDob("17/2024")); // missing month
        assertFalse(Dob.isValidDob("17/03")); // missing year
        assertFalse(Dob.isValidDob("03/2024")); // missing day
        assertFalse(Dob.isValidDob("17/2024")); // missing month

        // future date
        // Get today's date
        LocalDate today = LocalDate.now();
        // Get tomorrow's date
        LocalDate tomorrow = today.plusDays(1);
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format tomorrow's date
        String tomorrowFormatted = tomorrow.format(formatter);
        assertFalse(Dob.isValidDob(tomorrowFormatted));

        // valid Dob
        assertTrue(Dob.isValidDob("17/03/2024"));
    }

    @Test
    public void equals() {
        Dob Dob = new Dob("17/03/2024");

        // same values -> returns true
        assertTrue(Dob.equals(new Dob("17/03/2024")));

        // same object -> returns true
        assertTrue(Dob.equals(Dob));

        // null -> returns false
        assertFalse(Dob.equals(null));

        // different types -> returns false
        assertFalse(Dob.equals(17));

        // different values -> returns false
        assertFalse(Dob.equals(new Dob("16/03/2024")));
    }
}
