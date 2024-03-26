package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BirthdayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Birthday(null));
    }

    @Test
    public void constructor_invalidRoomNumber_throwsIllegalArgumentException() {
        String invalidBirthday = "";
        assertThrows(IllegalArgumentException.class, () -> new Birthday(invalidBirthday));
    }

    @Test
    public void isValidBirthday() {
        // null birthday
        assertThrows(NullPointerException.class, () -> Birthday.isValidBirthday(null));

        // invalid birthday
        assertFalse(Birthday.isValidBirthday("")); // empty string
        assertFalse(Birthday.isValidBirthday(" ")); // spaces only
        assertFalse(Birthday.isValidBirthday("12-12")); // missing year fields
        assertFalse(Birthday.isValidBirthday("30-02-2024")); // invalid day of month
        assertFalse(Birthday.isValidBirthday("12-12-2024")); // future date
        assertFalse(Birthday.isValidBirthday("12-13-2024")); // invalid month

        // valid birthday
        assertTrue(Birthday.isValidBirthday("12/12/2003"));
        assertTrue(Birthday.isValidBirthday("29/02/2024"));
        assertTrue(Birthday.isValidBirthday("26/03/2024"));
    }

    @Test
    public void equals() {
        Birthday birthday = new Birthday("01/01/2001");

        // same values -> returns true
        assertTrue(birthday.equals(new Birthday("01/01/2001")));

        // same object -> returns true
        assertTrue(birthday.equals(birthday));

        // null -> returns false
        assertFalse(birthday.equals(null));

        // different types -> returns false
        assertFalse(birthday.equals(5.0f));

        // different values -> returns false
        assertFalse(birthday.equals(new Birthday("01/02/2001")));
    }
}
