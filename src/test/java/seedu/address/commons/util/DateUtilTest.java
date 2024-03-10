package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateUtilTest {
    @Test
    public void isValidDateString() {
        // null date
        assertFalse(DateUtil.isValidDateString(null));

        // blank date
        assertFalse(DateUtil.isValidDateString("")); // empty string
        assertFalse(DateUtil.isValidDateString(" ")); // spaces only

        // invalid dates
        assertFalse(DateUtil.isValidDateString("91-12-2020")); // invalid day
        assertFalse(DateUtil.isValidDateString("31-13-2020")); // invalid month
        assertFalse(DateUtil.isValidDateString("31-12-20200")); // invalid year
        assertFalse(DateUtil.isValidDateString("31-12-2020 ")); // trailing space
        assertFalse(DateUtil.isValidDateString(" 31-12-2020")); // leading space
        assertFalse(DateUtil.isValidDateString("31- 12-2020")); // spaces within date
        assertFalse(DateUtil.isValidDateString("31-12- 2020")); // spaces within date
        assertFalse(DateUtil.isValidDateString("31-12-2020 12:00")); // time included
        assertFalse(DateUtil.isValidDateString("31-12-2020 12:00:00")); // time included
        assertFalse(DateUtil.isValidDateString("12-31-2020")); // MM-DD-YYYY format with hyphens
        assertFalse(DateUtil.isValidDateString("2020/12/31")); // YYYY/MM/DD format with slashes
        assertFalse(DateUtil.isValidDateString("12/31/2020")); // MM/DD/YYYY format with slashes
        assertFalse(DateUtil.isValidDateString("20201231")); // YYYYMMDD format without delimiters
        assertFalse(DateUtil.isValidDateString("12312020")); // MMDDYYYY format without delimiters
        assertFalse(DateUtil.isValidDateString("3 Jan 2021")); // d MMM YYYY format
        assertFalse(DateUtil.isValidDateString("31-Jan-2021")); // DD-MMM-YYYY format
        assertFalse(DateUtil.isValidDateString("January 3, 2021")); // Month d, YYYY format
        assertFalse(DateUtil.isValidDateString("2021-Jan-3")); // YYYY-MMM-d format
        assertFalse(DateUtil.isValidDateString("2020-12-3A")); // Alphabetic character in day component
        assertFalse(DateUtil.isValidDateString("2020-AB-31")); // Alphabetic characters in month component
        assertFalse(DateUtil.isValidDateString("ABCD-12-31")); // Alphabetic characters in year component
        assertFalse(DateUtil.isValidDateString("2020-12-31ABC")); // Alphabetic characters after the date

        // valid dates
        assertTrue(DateUtil.isValidDateString("2021-01-01"));
        assertTrue(DateUtil.isValidDateString("2022-02-28"));
        assertTrue(DateUtil.isValidDateString("2023-03-15"));
        assertTrue(DateUtil.isValidDateString("2024-04-30"));
        assertTrue(DateUtil.isValidDateString("2025-05-10"));
        assertTrue(DateUtil.isValidDateString("2026-06-20"));
        assertTrue(DateUtil.isValidDateString("2027-07-31"));
        assertTrue(DateUtil.isValidDateString("2028-08-15"));
        assertTrue(DateUtil.isValidDateString("2029-09-25"));
        assertTrue(DateUtil.isValidDateString("2030-10-05"));
    }

    @Test
    public void isParsableDateString() {

        // null date
        assertFalse(DateUtil.isValidDateString(null));

        // blank date
        assertFalse(DateUtil.isValidDateString("")); // empty string
        assertFalse(DateUtil.isValidDateString(" ")); // spaces only

        // invalid dates
        assertFalse(DateUtil.isValidDateString("91-12-2020")); // invalid day
        assertFalse(DateUtil.isValidDateString("31-13-2020")); // invalid month
        assertFalse(DateUtil.isValidDateString("31-12-20200")); // invalid year
        assertFalse(DateUtil.isValidDateString("31-12-2020 ")); // trailing space
        assertFalse(DateUtil.isValidDateString(" 31-12-2020")); // leading space
        assertFalse(DateUtil.isValidDateString("31- 12-2020")); // spaces within date
        assertFalse(DateUtil.isValidDateString("31-12- 2020")); // spaces within date
        assertFalse(DateUtil.isValidDateString("31-12-2020 12:00")); // time included
        assertFalse(DateUtil.isValidDateString("31-12-2020 12:00:00")); // time included
        assertFalse(DateUtil.isValidDateString("12-31-2020")); // MM-DD-YYYY format with hyphens
        assertFalse(DateUtil.isValidDateString("2020/12/31")); // YYYY/MM/DD format with slashes
        assertFalse(DateUtil.isValidDateString("12/31/2020")); // MM/DD/YYYY format with slashes
        assertFalse(DateUtil.isValidDateString("20201231")); // YYYYMMDD format without delimiters
        assertFalse(DateUtil.isValidDateString("12312020")); // MMDDYYYY format without delimiters
        assertFalse(DateUtil.isValidDateString("3 Jan 2021")); // d MMM YYYY format
        assertFalse(DateUtil.isValidDateString("31-Jan-2021")); // DD-MMM-YYYY format
        assertFalse(DateUtil.isValidDateString("January 3, 2021")); // Month d, YYYY format
        assertFalse(DateUtil.isValidDateString("2021-Jan-3")); // YYYY-MMM-d format
        assertFalse(DateUtil.isValidDateString("2020-12-3A")); // Alphabetic character in day component
        assertFalse(DateUtil.isValidDateString("2020-AB-31")); // Alphabetic characters in month component
        assertFalse(DateUtil.isValidDateString("ABCD-12-31")); // Alphabetic characters in year component
        assertFalse(DateUtil.isValidDateString("2020-12-31ABC")); // Alphabetic characters after the date

        // valid dates
        assertTrue(DateUtil.isValidDateString("2021-01-01"));
        assertTrue(DateUtil.isValidDateString("2022-02-28"));
        assertTrue(DateUtil.isValidDateString("2023-03-15"));
        assertTrue(DateUtil.isValidDateString("2024-04-30"));
        assertTrue(DateUtil.isValidDateString("2025-05-10"));
        assertTrue(DateUtil.isValidDateString("2026-06-20"));
        assertTrue(DateUtil.isValidDateString("2027-07-31"));
        assertTrue(DateUtil.isValidDateString("2028-08-15"));
        assertTrue(DateUtil.isValidDateString("2029-09-25"));
        assertTrue(DateUtil.isValidDateString("2030-10-05"));
    }

    @Test
    public void isPastDate() {
        // null date
        assertFalse(DateUtil.isPastDate(null));

        // future dates
        assertFalse(DateUtil.isPastDate(LocalDate.now().plusDays(1)));
        assertFalse(DateUtil.isPastDate(LocalDate.now().plusMonths(1)));
        assertFalse(DateUtil.isPastDate(LocalDate.now().plusYears(1)));

        // current date
        assertFalse(DateUtil.isPastDate(LocalDate.now()));

        // past dates
        assertTrue(DateUtil.isPastDate(LocalDate.now().minusDays(1)));
        assertTrue(DateUtil.isPastDate(LocalDate.now().minusMonths(1)));
        assertTrue(DateUtil.isPastDate(LocalDate.now().minusYears(1)));
    }

    @Test
    public void parseStringToDate() {
        // null date
        assertThrows(NullPointerException.class, () -> DateUtil.parseStringToDate(null));

        // blank date
        assertThrows(DateTimeParseException.class, () -> DateUtil.parseStringToDate("")); // empty string
        assertThrows(DateTimeParseException.class, () -> DateUtil.parseStringToDate(" ")); // spaces only

        // invalid dates
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("91-12-2020")); // invalid day
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-13-2020")); // invalid month
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-12-20200")); // invalid year
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-12-2020 ")); // trailing space
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate(" 31-12-2020")); // leading space
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31- 12-2020")); // spaces within date
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-12- 2020")); // spaces within date
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-12-2020 12:00")); // time included
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-12-2020 12:00:00")); // time included
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("12-31-2020")); // MM-DD-YYYY format with hyphens
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("2020/12/31")); // YYYY/MM/DD format with slashes
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("12/31/2020")); // MM/DD/YYYY format with slashes
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("20201231")); // YYYYMMDD format without delimiters
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("12312020")); // MMDDYYYY format without delimiters
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("3 Jan 2021")); // d MMM YYYY format
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("31-Jan-2021")); // DD-MMM-YYYY format
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("January 3, 2021")); // Month d, YYYY format
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("2021-Jan-3")); // YYYY-MMM-d format
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("2020-12-3A")); // Alphabetic character in day component
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("2020-AB-31")); // Alphabetic characters in month component
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("ABCD-12-31")); // Alphabetic characters in year component
        assertThrows(DateTimeParseException.class, () ->
                DateUtil.parseStringToDate("2020-12-31ABC")); // Alphabetic characters after the date

        // valid dates
        assertEquals(DateUtil.parseStringToDate("2021-01-01"), LocalDate.of(2021, 1, 1));

        // valid dates
        assertEquals(DateUtil.parseStringToDate("2021-01-01"), LocalDate.of(2021, 1, 1));
        assertEquals(DateUtil.parseStringToDate("2022-02-28"), LocalDate.of(2022, 2, 28));
        assertEquals(DateUtil.parseStringToDate("2023-03-15"), LocalDate.of(2023, 3, 15));
        assertEquals(DateUtil.parseStringToDate("2024-04-30"), LocalDate.of(2024, 4, 30));
        assertEquals(DateUtil.parseStringToDate("2025-05-10"), LocalDate.of(2025, 5, 10));
        assertEquals(DateUtil.parseStringToDate("2026-06-20"), LocalDate.of(2026, 6, 20));
        assertEquals(DateUtil.parseStringToDate("2027-07-31"), LocalDate.of(2027, 7, 31));
        assertEquals(DateUtil.parseStringToDate("2028-08-15"), LocalDate.of(2028, 8, 15));
        assertEquals(DateUtil.parseStringToDate("2029-09-25"), LocalDate.of(2029, 9, 25));
        assertEquals(DateUtil.parseStringToDate("2030-10-05"), LocalDate.of(2030, 10, 5));
    }

    @Test
    public void parseDateToString() {
        // null date
        assertEquals(DateUtil.parseDateToString(null), "");

        // valid dates
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2021, 1, 1)), "2021-01-01");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2022, 2, 28)), "2022-02-28");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2023, 3, 15)), "2023-03-15");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2024, 4, 30)), "2024-04-30");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2025, 5, 10)), "2025-05-10");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2026, 6, 20)), "2026-06-20");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2027, 7, 31)), "2027-07-31");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2028, 8, 15)), "2028-08-15");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2029, 9, 25)), "2029-09-25");
        assertEquals(DateUtil.parseDateToString(LocalDate.of(2030, 10, 5)), "2030-10-05");
    }

    @Test
    void getMessageConstraintsForDateType() {
        String dateType = "Birthday";
        String expectedMessage = dateType + " " + DateUtil.MESSAGE_CONSTRAINTS;
        assertEquals(expectedMessage, DateUtil.getMessageConstraintsForDateType(dateType));
    }
}
