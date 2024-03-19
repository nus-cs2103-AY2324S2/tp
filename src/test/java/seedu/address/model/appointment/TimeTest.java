package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // blank time
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only

        // missing parts
        assertFalse(Time.isValidTime(":00")); // missing HH part
        assertFalse(Time.isValidTime("12:")); // missing mm part
        assertFalse(Time.isValidTime("1200")); // missing ':' symbol

        // invalid parts
        assertFalse(Time.isValidTime("24:00")); // invalid HH part
        assertFalse(Time.isValidTime("1200:00")); // invalid HH part
        assertFalse(Time.isValidTime("012:00")); // invalid HH part
        assertFalse(Time.isValidTime("12:60")); // invalid mm part
        assertFalse(Time.isValidTime("12:555")); // invalid mm part
        assertFalse(Time.isValidTime("12:0")); // invalid mm part
        assertFalse(Time.isValidTime("12/50")); // invalid delimiter
        assertFalse(Time.isValidTime("12-50")); // invalid delimiter
        assertFalse(Time.isValidTime("12::50")); // invalid delimiter
        assertFalse(Time.isValidTime("12:50:22")); // ss given
        assertFalse(Time.isValidTime(" 12:50")); // leading space
        assertFalse(Time.isValidTime("12:50 ")); // trailing space

        // valid time
        assertTrue(Time.isValidTime("23:59")); // 24-hour clock
        assertTrue(Time.isValidTime("11:59")); // 24-hour clock
        assertTrue(Time.isValidTime("00:00")); // 24-hour clock
    }

    @Test
    public void equals() {
        Time time = new Time("12:00");

        // same values -> returns true
        assertTrue(time.equals(new Time("12:00")));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals(5.0f));

        // different values -> returns false
        assertFalse(time.equals(new Time("00:00")));
    }
}
