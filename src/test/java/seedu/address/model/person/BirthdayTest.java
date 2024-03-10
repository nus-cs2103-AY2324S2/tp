package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class BirthdayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidBirthday_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null date
        assertFalse(Birthday.isValidBirthday(null));

        // blank date
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only

        // invalid dates
        assertFalse(Birthday.isValidBirthday("91-12-2020")); // invalid day
        assertFalse(Birthday.isValidBirthday("31-13-2020")); // invalid month
        assertFalse(Birthday.isValidBirthday("31-12-20200")); // invalid year
        assertFalse(Birthday.isValidBirthday("31-12-2020 ")); // trailing space
        assertFalse(Birthday.isValidBirthday(" 31-12-2020")); // leading space
        assertFalse(Birthday.isValidBirthday("31- 12-2020")); // spaces within date
        assertFalse(Birthday.isValidBirthday("31-12- 2020")); // spaces within date
        assertFalse(Birthday.isValidBirthday("31-12-2020 12:00")); // time included
        assertFalse(Birthday.isValidBirthday("31-12-2020 12:00:00")); // time included
        assertFalse(Birthday.isValidBirthday("12-31-2020")); // MM-DD-YYYY format with hyphens
        assertFalse(Birthday.isValidBirthday("2020/12/31")); // YYYY/MM/DD format with slashes
        assertFalse(Birthday.isValidBirthday("12/31/2020")); // MM/DD/YYYY format with slashes
        assertFalse(Birthday.isValidBirthday("20201231")); // YYYYMMDD format without delimiters
        assertFalse(Birthday.isValidBirthday("12312020")); // MMDDYYYY format without delimiters
        assertFalse(Birthday.isValidBirthday("3 Jan 2021")); // d MMM YYYY format
        assertFalse(Birthday.isValidBirthday("31-Jan-2021")); // DD-MMM-YYYY format
        assertFalse(Birthday.isValidBirthday("January 3, 2021")); // Month d, YYYY format
        assertFalse(Birthday.isValidBirthday("2021-Jan-3")); // YYYY-MMM-d format
        assertFalse(Birthday.isValidBirthday("2020-12-3A")); // Alphabetic character in day component
        assertFalse(Birthday.isValidBirthday("2020-AB-31")); // Alphabetic characters in month component
        assertFalse(Birthday.isValidBirthday("ABCD-12-31")); // Alphabetic characters in year component
        assertFalse(Birthday.isValidBirthday("2020-12-31ABC")); // Alphabetic characters after the date

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plusDays(1)
                .format(formatter))); // future date
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plusMonths(1)
                .format(formatter))); // future date
        assertFalse(Birthday.isValidBirthday(LocalDate.now().plusYears(1)
                .format(formatter))); // future date

        // valid dates
        assertTrue(Birthday.isValidBirthday("1992-01-01"));
        assertTrue(Birthday.isValidBirthday("1993-02-28"));
        assertTrue(Birthday.isValidBirthday("1994-03-15"));
        assertTrue(Birthday.isValidBirthday("1995-04-30"));
        assertTrue(Birthday.isValidBirthday("1996-05-10"));
        assertTrue(Birthday.isValidBirthday("1997-06-20"));
        assertTrue(Birthday.isValidBirthday("1998-07-31"));
        assertTrue(Birthday.isValidBirthday("1999-08-15"));
        assertTrue(Birthday.isValidBirthday("2000-09-25"));
        assertTrue(Birthday.isValidBirthday("2001-10-05"));
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("1993-09-22");

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday("1993-09-22")));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("1993-09-23")));
    }
}
