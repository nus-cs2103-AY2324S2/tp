package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImportantDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportantDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ImportantDate("something"));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String validDate = "01-01-2022";

        assertThrows(IllegalArgumentException.class, () -> new ImportantDate(validDate + ", HH:mm - HH:mm"));
        assertThrows(IllegalArgumentException.class, () -> new ImportantDate(validDate + ", 99:88 - 99:99"));
    }

    @Test
    public void isValidImportantDate() {
        assertThrows(NullPointerException.class, () -> ImportantDate.isValidImportantDate(null));

        assertFalse(ImportantDate.isValidImportantDate(""));
        assertFalse(ImportantDate.isValidImportantDate("   "));
        assertFalse(ImportantDate.isValidImportantDate("01-01-2022, HH:mm - HH:mm"));
        assertFalse(ImportantDate.isValidImportantDate("01-01-2022, 99:99 - 99:99"));
        assertFalse(ImportantDate.isValidImportantDate("01-01-2022, 12:12 - 99:99"));
        assertFalse(ImportantDate.isValidImportantDate("01-01-2022, 12:12 to 12:12"));

        assertTrue(ImportantDate.isValidImportantDate("01-01-2022"));
        assertTrue(ImportantDate.isValidImportantDate("01-01-2022, 12:12 - 12:12        "));
        assertTrue(ImportantDate.isValidImportantDate("          01-01-2022, 12:12 - 12:12"));
    }

    @Test
    public void equals() {
        ImportantDate date = new ImportantDate("01-01-2022, 12:12 - 12:12");

        assertFalse(date.equals(null));
        assertFalse(date.equals("Something"));
        assertFalse(date.equals(10));
        assertFalse(date.equals(new ImportantDate("02-01-2022, 12:12 - 12:12")));
        assertFalse(date.equals(new ImportantDate("01-02-2022, 12:12 - 12:12")));
        assertFalse(date.equals(new ImportantDate("01-02-2023, 12:12 - 12:12")));
        assertFalse(date.equals(new ImportantDate("01-01-2022, 12:12 - 12:11")));
        assertFalse(date.equals(new ImportantDate("01-01-2022, 12:12 - 11:12")));
        assertFalse(date.equals(new ImportantDate("01-01-2022, 12:11 - 12:12")));
        assertFalse(date.equals(new ImportantDate("01-01-2022, 11:12 - 12:12")));

        assertTrue(date.equals(date));
        assertTrue(date.equals(new ImportantDate("01-01-2022, 12:12 - 12:12")));
    }

    @Test
    public void dateToString() {
        ImportantDate date = new ImportantDate("01-01-2022");

        assertTrue(date.toString().equals("(01-01-2022)"));
    }

    @Test
    public void dateTimeToString() {
        ImportantDate date = new ImportantDate("01-01-2022, 12:12 - 12:12");

        assertTrue(date.toString().equals("(01-01-2022, from 12:12 to 12:12)"));
    }
}
