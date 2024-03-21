package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class AdmissionDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AdmissionDate(null));
    }

    @Test
    public void constructor_invalidAdmissionDate_throwsIllegalArgumentException() {
        String invalidAdmissionDate = "";
        assertThrows(IllegalArgumentException.class, () -> new AdmissionDate(invalidAdmissionDate));
    }

    @Test
    public void isValidAdmissionDate() {
        // null AdmissionDate
        assertThrows(NullPointerException.class, () -> AdmissionDate.isValidAdmissionDate(null));

        // blank AdmissionDate
        assertFalse(AdmissionDate.isValidAdmissionDate("")); // empty string
        assertFalse(AdmissionDate.isValidAdmissionDate(" ")); // spaces only

        // missing parts
        assertFalse(AdmissionDate.isValidAdmissionDate("17/03/")); // missing year
        assertFalse(AdmissionDate.isValidAdmissionDate("/03/2024")); // missing day
        assertFalse(AdmissionDate.isValidAdmissionDate("17/2024")); // missing month
        assertFalse(AdmissionDate.isValidAdmissionDate("17/03")); // missing year
        assertFalse(AdmissionDate.isValidAdmissionDate("03/2024")); // missing day
        assertFalse(AdmissionDate.isValidAdmissionDate("17/2024")); // missing month

        // future date
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        // Define date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Format date
        String todayFormatted = today.format(formatter);
        String tomorrowFormatted = tomorrow.format(formatter);
        assertFalse(AdmissionDate.isValidAdmissionDate(tomorrowFormatted));

        // valid AdmissionDate
        assertTrue(AdmissionDate.isValidAdmissionDate("17/03/2024"));
        assertTrue(AdmissionDate.isValidAdmissionDate(todayFormatted));
    }

    @Test
    public void equals() {
        AdmissionDate admissionDate = new AdmissionDate("17/03/2024");

        // same values -> returns true
        assertTrue(admissionDate.equals(new AdmissionDate("17/03/2024")));

        // same object -> returns true
        assertTrue(admissionDate.equals(admissionDate));

        // null -> returns false
        assertFalse(admissionDate.equals(null));

        // different types -> returns false
        assertFalse(admissionDate.equals(17));

        // different values -> returns false
        assertFalse(admissionDate.equals(new AdmissionDate("16/03/2024")));
    }
}
