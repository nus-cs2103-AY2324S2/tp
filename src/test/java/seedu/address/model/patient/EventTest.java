package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null, null));
        assertThrows(NullPointerException.class, () -> new Event(null, "01-01-2022"));
        assertThrows(NullPointerException.class, () -> new Event("Family Visit", null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event("Family Visit", "something"));
        assertThrows(IllegalArgumentException.class, () -> new Event("Family Visit", "1-1-2022"));
        assertThrows(IllegalArgumentException.class, () -> new Event("Family Visit", "01-01-22"));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String validDate = "01-01-2022";

        assertThrows(IllegalArgumentException.class, () -> new Event("Family Visit",
                validDate + ", HH:mm - HH:mm"));
        assertThrows(IllegalArgumentException.class, () -> new Event("Family Visit",
                validDate + "21-02-2022, 99:88 - 99:99"));
        assertThrows(IllegalArgumentException.class, () -> new Event("Family Visit",
                validDate + "21-02-2022, 0000 - 2359"));
    }

    @Test
    public void isValidEvent() {
        assertThrows(NullPointerException.class, () -> Event.isValidEvent(null));

        assertFalse(Event.isValidEvent(""));
        assertFalse(Event.isValidEvent("   "));
        assertFalse(Event.isValidEvent("01-01-2022, HH:mm - HH:mm"));
        assertFalse(Event.isValidEvent("01-01-2022, 99:99 - 99:99"));
        assertFalse(Event.isValidEvent("01-01-2022, 12:12 - 99:99"));
        assertFalse(Event.isValidEvent("01-01-2022, 12:12 to 12:12"));

        assertTrue(Event.isValidEvent("01-01-2022       "));
        assertTrue(Event.isValidEvent("       01-01-2022"));
        assertTrue(Event.isValidEvent("01-01-2022"));
        assertTrue(Event.isValidEvent("21-02-2022, 01:00 - 19:00"));
        assertTrue(Event.isValidEvent("21-02-2022, 00:00 - 23:59"));
        assertTrue(Event.isValidEvent("01-01-2022, 12:12 - 12:12        "));
        assertTrue(Event.isValidEvent("          01-01-2022, 12:12 - 12:12"));
    }

    @Test
    public void equals() {
        Event date = new Event("Family Visit", "01-01-2022, 12:12 - 12:12");

        assertFalse(date.equals(null));
        assertFalse(date.equals("Something"));
        assertFalse(date.equals(10));
        assertFalse(date.equals(new Event("Family Visit", "02-01-2022, 12:12 - 12:12")));
        assertFalse(date.equals(new Event("Family Visit", "01-02-2022, 12:12 - 12:12")));
        assertFalse(date.equals(new Event("Family Visit", "01-02-2023, 12:12 - 12:12")));
        assertFalse(date.equals(new Event("Family Visit", "01-01-2022, 12:12 - 12:11")));
        assertFalse(date.equals(new Event("Family Visit", "01-01-2022, 12:12 - 11:12")));
        assertFalse(date.equals(new Event("Family Visit", "01-01-2022, 12:11 - 12:12")));
        assertFalse(date.equals(new Event("Family Visit", "01-01-2022, 11:12 - 12:12")));

        assertTrue(date.equals(date));
        assertTrue(date.equals(new Event("Family Visit", "01-01-2022, 12:12 - 12:12")));
    }

    @Test
    public void dateToString() {
        Event date = new Event("Family Visit", "01-01-2022");

        assertTrue(date.toString().equals("Family Visit (01-01-2022)"));
    }

    @Test
    public void dateTimeToString() {
        Event date = new Event("Family Visit", "01-01-2022, 12:12 - 12:12");

        assertTrue(date.toString().equals("Family Visit (01-01-2022, from 12:12 to 12:12)"));
    }
}
