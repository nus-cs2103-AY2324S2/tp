package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.Birthday.BIRTHDAY_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "asdf";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidDate));
    }

    @Test
    public void constructor_dateAfterPresent_throwsIllegalArgumentException() {
        String futureDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(BIRTHDAY_FORMAT));
        assertThrows(IllegalArgumentException.class, () -> new Birthday(futureDate));
    }

    @Test
    public void isValidBirthday() {
        // invalid birthdays
        assertFalse(Birthday.isValidBirthday("asdf")); // non-date string
        assertFalse(Birthday.isValidBirthday("31/02/1998")); // invalid date

        // valid addresses
        assertTrue(Birthday.isValidBirthday(null)); // null will be treated as no birthday
        assertTrue(Birthday.isValidBirthday("")); // empty strings treated as no birthday
        assertTrue(Birthday.isValidBirthday(" ")); // pure whitespace treated as no birthday
        assertTrue(Birthday.isValidBirthday("01/02/1999")); // valid date
        assertTrue(Birthday.isValidBirthday(" 01/02/1999 ")); // valid date with whitespaces
        assertTrue(Birthday.isValidBirthday("29/02/2000")); // leap year
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("01/02/1999");

        // same values -> returns true
        assertEquals(birthday, new Birthday("01/02/1999"));

        // same object -> returns true
        assertEquals(birthday, birthday);

        // null -> returns false
        assertNotEquals(null, birthday);

        // different types -> returns false
        assertNotEquals(5.0f, birthday);

        // different values -> returns false
        assertNotEquals(birthday, new Birthday("02/03/1998"));
    }
}
