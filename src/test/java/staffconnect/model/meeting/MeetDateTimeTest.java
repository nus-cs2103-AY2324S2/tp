package staffconnect.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class MeetDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetDateTime(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetDateTime(invalidDate));
    }

    @Test
    public void isValidMeetDateTime() {
        // null Date
        assertThrows(NullPointerException.class, () -> MeetDateTime.isValidMeetDateTime(null));

        // invalid Meeting Date
        assertFalse(MeetDateTime.isValidMeetDateTime("")); // empty string
        assertFalse(MeetDateTime.isValidMeetDateTime(" ")); // spaces only
        assertFalse(MeetDateTime.isValidMeetDateTime("ABCD")); // letters only
        assertFalse(MeetDateTime.isValidMeetDateTime("1234")); // numbers only
        assertFalse(MeetDateTime.isValidMeetDateTime("120420231200")); // missing separator no space
        assertFalse(MeetDateTime.isValidMeetDateTime("12/0420231200")); // only 1 separator no space
        assertFalse(MeetDateTime.isValidMeetDateTime("1204/2023 1200")); // only 1 separator
        assertFalse(MeetDateTime.isValidMeetDateTime("12-04-2023 12:00")); // wrong separator
        assertFalse(MeetDateTime.isValidMeetDateTime("12/04/23 12:00")); // wrong digits for year
        assertFalse(MeetDateTime.isValidMeetDateTime("12/4/2023 12:00")); // wrong number digits for month
        assertFalse(MeetDateTime.isValidMeetDateTime("1/04/2023 12:00")); // wrong number digits for day
        assertFalse(MeetDateTime.isValidMeetDateTime("99/04/2023 12:00")); // wrong date values
        assertFalse(MeetDateTime.isValidMeetDateTime("01/04/2023 99:00")); // wrong time values
        // date does not exist, but error not caught by a normal parse operation. Only strict would catch this.
        assertFalse(MeetDateTime.isValidMeetDateTime("30/02/2024 12:00"));
        assertFalse(MeetDateTime.isValidMeetDateTime("29/02/2023 12:00")); //2023 is not a leap year


        // valid meeting Date
        assertTrue(MeetDateTime.isValidMeetDateTime("12/04/2023 12:00")); // dd/MM/yyyy HH:mm
        assertTrue(MeetDateTime.isValidMeetDateTime("15/02/2024 12:00")); // dd/MM/yyyy HH:mm
        assertTrue(MeetDateTime.isValidMeetDateTime("29/02/2024 12:00")); //valid date on a leap year
    }

    @Test
    public void equals() {
        MeetDateTime date = new MeetDateTime("20/01/2023 12:00");
        Description testDescription = new Description("Valid Description");

        // same values -> returns true
        assertEquals(date, new MeetDateTime("20/01/2023 12:00"));

        // same object -> returns true
        assertEquals(date, date);

        // null -> returns false
        assertNotEquals(null, date);

        // different types -> returns false
        assertNotEquals(1234, date);

        //Different object type -> returns false
        assertFalse(date.equals(testDescription));

        // different values -> returns false
        assertNotEquals(date, new MeetDateTime("15/02/2024 12:00"));
    }

    @Test
    public void asSymmetricHashcode() {
        MeetDateTime first = new MeetDateTime("12/04/2023 12:00");
        MeetDateTime second = new MeetDateTime("12/04/2023 12:00");
        assertEquals(first.hashCode(), second.hashCode());
    }


}
