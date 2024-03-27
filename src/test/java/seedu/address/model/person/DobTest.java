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
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        // Define date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format date
        String todayFormatted = today.format(formatter);
        String tomorrowFormatted = tomorrow.format(formatter);
        assertFalse(Dob.isValidDob(tomorrowFormatted));

        // valid Dob
        assertTrue(Dob.isValidDob("17/03/2024"));
        assertTrue(Dob.isValidDob(todayFormatted));
    }

    @Test
    public void equals() {
        Dob dob = new Dob("17/03/2024");

        // same values -> returns true
        assertTrue(dob.equals(new Dob("17/03/2024")));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // null -> returns false
        assertFalse(dob.equals(null));

        // different types -> returns false
        assertFalse(dob.equals(17));

        // different values -> returns false
        assertFalse(dob.equals(new Dob("16/03/2024")));
    }
}
