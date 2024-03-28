package staffconnect.model.availability;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class AvailabilityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Availability(null));
    }

    @Test
    public void constructor_invalidAvailability_throwsIllegalArgumentException() {
        String invalidAvailability = "";
        assertThrows(IllegalArgumentException.class, () -> new Availability(invalidAvailability));
    }

    @Test
    public void isValidAvailability() {
        // null availability
        assertThrows(NullPointerException.class, () -> Availability.isValidAvailability(null));

        // invalid availability
        assertFalse(Availability.isValidAvailability("")); // empty string
        assertFalse(Availability.isValidAvailability(" ")); // spaces only
        assertFalse(Availability.isValidAvailability("m")); // 1 letter only
        assertFalse(Availability.isValidAvailability("1")); // 1 number only
        assertFalse(Availability.isValidAvailability("m o n   1 2 : 0 0   1 4 : 0 0")); // spaces between letters
        assertFalse(Availability.isValidAvailability("birthday 12:00 14:00")); // incorrect type of day
        assertFalse(Availability.isValidAvailability("moon 12:00 14:00 ")); // 1 letter duplicated
        assertFalse(Availability.isValidAvailability("mon123 12:00 14:00")); // trailing number behind day
        assertFalse(Availability.isValidAvailability("#@$!")); // special characters
        assertFalse(Availability.isValidAvailability("mon#@$!")); // prefix and special characters
        assertFalse(Availability.isValidAvailability("123#@$!")); // numbers and special characters
        assertFalse(Availability.isValidAvailability("day")); // day without prefix
        assertFalse(Availability.isValidAvailability("sytaudrh")); // scrambled spelling
        assertFalse(Availability.isValidAvailability("chewsday 11:00 13:00")); // misspelling
        assertFalse(Availability.isValidAvailability("th")); // uncompleted syllable
        assertFalse(Availability.isValidAvailability("sun day 11:00 13:00")); // space between syllable
        assertFalse(Availability.isValidAvailability("sat day 11:00 13:00")); // missing middle syllable
        assertFalse(Availability.isValidAvailability("satuday 11:00 13:00")); // missing middle letter
        assertFalse(Availability.isValidAvailability("MONDAY 1200 1400")); // missing : divider in HH:mm format
        assertFalse(Availability.isValidAvailability("MONDAY 1PM 2PM")); // both time not in HH:mm format
        assertFalse(Availability.isValidAvailability("MONDAY 1PM 14:00")); // start time not in HH:mm format
        assertFalse(Availability.isValidAvailability("MONDAY 13:00 2PM")); // end time not in HH:mm format
        assertFalse(Availability.isValidAvailability("MONDAY 8:00 9:00")); // both time missing leading 0
        assertFalse(Availability.isValidAvailability("MONDAY 8:00 09:00")); // start time missing leading 0
        assertFalse(Availability.isValidAvailability("MONDAY 08:00 9:00")); // end time missing leading 0
        assertFalse(Availability.isValidAvailability("MONDAY 08:0 09:0")); // both time missing trailing 0
        assertFalse(Availability.isValidAvailability("MONDAY 08: 09:")); // both time missing trailing 00
        assertFalse(Availability.isValidAvailability("MONDAY 08:000 09:000")); // both time extra trailing 0
        assertFalse(Availability.isValidAvailability("MONDAY 15:00 15:00")); // start time same as end time
        assertFalse(Availability.isValidAvailability("MONDAY 16:00 15:00")); // start time after end time, by hours
        assertFalse(Availability.isValidAvailability("MONDAY 15:30 15:00")); // start time after end time, by minutes
        assertFalse(Availability.isValidAvailability("MONDAY 25:00 26:00")); // HH more than 23
        assertFalse(Availability.isValidAvailability("MONDAY 18:80 18:90")); // mm more than 23


        // valid availability
        assertTrue(Availability.isValidAvailability("mon 14:00 16:00")); // first syllable tuesday
        assertTrue(Availability.isValidAvailability("tue 14:00 16:00")); // first syllable tuesday without s
        assertTrue(Availability.isValidAvailability("tues 14:00 16:00")); // first syllable tuesday with s
        assertTrue(Availability.isValidAvailability("wed 14:00 16:00")); // first syllable wednesday
        assertTrue(Availability.isValidAvailability("wednes 14:00 16:00")); // two syllable wednesday
        assertTrue(Availability.isValidAvailability("thu 14:00 16:00")); // first syllable thursday without rs
        assertTrue(Availability.isValidAvailability("thur 14:00 16:00")); // first syllable thursday without s
        assertTrue(Availability.isValidAvailability("thurs 14:00 16:00")); // first syllable thursday with s
        assertTrue(Availability.isValidAvailability("sat 14:00 16:00")); // first syllable saturday
        assertTrue(Availability.isValidAvailability("satur 14:00 16:00")); // two syllable saturday
        assertTrue(Availability.isValidAvailability("sun 14:00 16:00")); // first syllable sunday
        assertTrue(Availability.isValidAvailability("Sun 14:00 16:00")); // first syllable with capitalised first letter
        assertTrue(Availability.isValidAvailability("sAtUr 14:00 16:00")); // two syllable mixed case
        assertTrue(Availability.isValidAvailability("monday 14:00 16:00")); // full word lowercase monday
        assertTrue(Availability.isValidAvailability("tuesday 14:00 16:00")); // full word lowercase tuesday
        assertTrue(Availability.isValidAvailability("wednesday 14:00 16:00")); // full word lowercase wednesday
        assertTrue(Availability.isValidAvailability("thursday 14:00 16:00")); // full word lowercase thursday
        assertTrue(Availability.isValidAvailability("friday 14:00 16:00")); // full word lowercase friday
        assertTrue(Availability.isValidAvailability("saturday 14:00 16:00")); // full word lowercase saturday
        assertTrue(Availability.isValidAvailability("sunday 14:00 16:00")); // full word lowercase sunday
        assertTrue(Availability.isValidAvailability("MONDAY 14:00 16:00")); // full word uppercase monday
        assertTrue(Availability.isValidAvailability("Tuesday 14:00 16:00")); // full word with capitalised first letter
        assertTrue(Availability.isValidAvailability("wEdNeSdAy 14:00 16:00")); // full word mixed case

        assertTrue(Availability.isValidAvailability("Tuesday 14:00 16:00")); // 00:00
        assertTrue(Availability.isValidAvailability("Tuesday 00:00 01:00")); // 00:00
        assertTrue(Availability.isValidAvailability("Tuesday 00:00 23:59")); // whole day
        assertTrue(Availability.isValidAvailability("Tuesday 12:34 12:35")); // 1 min interval
        assertTrue(Availability.isValidAvailability("Tuesday 11:33 22:55")); // random valid times 1
        assertTrue(Availability.isValidAvailability("Tuesday 10:00 20:00")); // random valid times 2
        assertTrue(Availability.isValidAvailability("Tuesday 00:00 00:01")); // random valid times 3
    }

    @Test
    public void equals() {
        Availability monAvailability = new Availability("monday 12:00 13:00");

        // same values -> returns true
        assertTrue(monAvailability.equals(new Availability("monday 12:00 13:00")));

        // same day and time -> return true
        assertTrue(monAvailability.equals(new Availability("mon 12:00 13:00")));

        // same object -> returns true
        assertTrue(monAvailability.equals(monAvailability));

        // null -> returns false
        assertFalse(monAvailability.equals(null));

        // different types -> returns false
        assertFalse(monAvailability.equals(5.0f));

        // different values, different day -> returns false
        assertFalse(monAvailability.equals(new Availability("tuesday 12:00 13:00")));

        // different values, different time -> returns false
        assertFalse(monAvailability.equals(new Availability("monday 11:00 12:00")));

    }

    @Test
    public void parseToDayOfWeekTest() {
        assertEquals(DayOfWeek.TUESDAY, Availability.parseToDayOfWeek("tues"));
        assertEquals(DayOfWeek.WEDNESDAY, Availability.parseToDayOfWeek("wednes"));
        assertEquals(DayOfWeek.THURSDAY, Availability.parseToDayOfWeek("thursday"));
        assertEquals(DayOfWeek.FRIDAY, Availability.parseToDayOfWeek("FRIDAY"));
        assertEquals(DayOfWeek.SATURDAY, Availability.parseToDayOfWeek("SATurday"));
        assertEquals(DayOfWeek.SUNDAY, Availability.parseToDayOfWeek("sunDAY"));
        assertNotEquals(DayOfWeek.SUNDAY, Availability.parseToDayOfWeek("mon"));
        assertNotEquals(DayOfWeek.SATURDAY, Availability.parseToDayOfWeek("friday"));
    }

    @Test
    public void parseToLocalTimeTest() {
        assertEquals(LocalTime.NOON, Availability.parseToLocalTime("12:00"));
        assertEquals(LocalTime.MIDNIGHT, Availability.parseToLocalTime("00:00"));
        assertEquals(LocalTime.MIN, Availability.parseToLocalTime("00:00"));
        assertNotEquals(LocalTime.MIN, Availability.parseToLocalTime("00:01"));
    }

    @Test
    public void isValidAvailabilityTest() {
        assertTrue(Availability.isValidAvailability("mon 12:00 14:00"));
        assertTrue(Availability.isValidAvailability("monday 12:00 14:00"));
        assertTrue(Availability.isValidAvailability("tues 02:00 04:00"));
        assertTrue(Availability.isValidAvailability("tuesday 11:00 11:30"));
        assertTrue(Availability.isValidAvailability("wed 11:00 11:30"));
        assertTrue(Availability.isValidAvailability("wednesday 11:00 11:30"));
        assertTrue(Availability.isValidAvailability("thurs 21:00 21:30"));
        assertTrue(Availability.isValidAvailability("thursday 21:00 21:30"));
        assertTrue(Availability.isValidAvailability("fri 23:00 23:59"));
        assertTrue(Availability.isValidAvailability("friday 23:00 23:59"));


        assertFalse(Availability.isValidAvailability("")); //nothing
        assertFalse(Availability.isValidAvailability(" ")); //spaces
        assertFalse(Availability.isValidAvailability("!@#$%^&*IO 3:00 24:59")); //day contain special characters
        assertFalse(Availability.isValidAvailability("friday -3:00 23:59")); //negative values
        assertFalse(Availability.isValidAvailability("friday 3:00 24:59")); //hour exceed 23
        assertFalse(Availability.isValidAvailability("friday 3:00 23:60")); //minutes exceed 59
        assertFalse(Availability.isValidAvailability("friday 3:00 23:59")); //missing start time leading 0
        assertFalse(Availability.isValidAvailability("friday 01:00 3:00")); //missing end time leading 0
        assertFalse(Availability.isValidAvailability("friday 02:0 01:00")); //missing start time trailing 0
        assertFalse(Availability.isValidAvailability("friday 02:00 01:0")); //missing end time trailing 0
        assertFalse(Availability.isValidAvailability("friday 02: 02:00")); //missing start time trailing 00
        assertFalse(Availability.isValidAvailability("friday 02:00 02:")); //missing end time trailing 00
        assertFalse(Availability.isValidAvailability("friday 02: 02:")); //missing both time trailing 00
        assertFalse(Availability.isValidAvailability("friday 0230 0200")); //missing ':'
        assertFalse(Availability.isValidAvailability("friday02:3002:00")); // no space between day and times
        assertFalse(Availability.isValidAvailability("july 02:30 02:00")); //invalid day
    }

    @Test
    public void isValidLocalTimeTest() {
        assertTrue(Availability.isValidLocalTime("00:00"));
        assertTrue(Availability.isValidLocalTime("00:01"));
        assertTrue(Availability.isValidLocalTime("00:10"));
        assertTrue(Availability.isValidLocalTime("01:00"));
        assertTrue(Availability.isValidLocalTime("10:00"));
        assertTrue(Availability.isValidLocalTime("23:59"));
        assertTrue(Availability.isValidLocalTime("13:00"));

        assertFalse(Availability.isValidLocalTime("")); //nothing
        assertFalse(Availability.isValidLocalTime(" ")); //spaces
        assertFalse(Availability.isValidLocalTime("!@#$%^&*IO")); //day contain special characters
        assertFalse(Availability.isValidLocalTime("3:00")); //missing leading 0
        assertFalse(Availability.isValidLocalTime("02:0")); //missing time trailing 0
        assertFalse(Availability.isValidLocalTime("02:")); //missing end time trailing 00
        assertFalse(Availability.isValidLocalTime("02")); // missing leading or trailing time
        assertFalse(Availability.isValidLocalTime("0230 0200")); //missing ':'

    }
}
