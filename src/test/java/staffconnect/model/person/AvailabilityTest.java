package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import java.time.DayOfWeek;

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
        assertFalse(Availability.isValidAvailability("m o n")); // spaces between letters
        assertFalse(Availability.isValidAvailability("birthday")); // incorrect type of day
        assertFalse(Availability.isValidAvailability("moon")); // 1 letter duplicated
        assertFalse(Availability.isValidAvailability("mon123")); // trailing number behind day
        assertFalse(Availability.isValidAvailability("#@$!")); // special characters
        assertFalse(Availability.isValidAvailability("mon#@$!")); // prefix and special characters
        assertFalse(Availability.isValidAvailability("123#@$!")); // numbers and special characters
        assertFalse(Availability.isValidAvailability("day")); // day without prefix
        assertFalse(Availability.isValidAvailability("sytaudrh")); // scrambled spelling
        assertFalse(Availability.isValidAvailability("chewsday")); // misspelling
        assertFalse(Availability.isValidAvailability("th")); // uncompleted syllable
        assertFalse(Availability.isValidAvailability("sun day")); // space between syllable
        assertFalse(Availability.isValidAvailability("sat day")); // missing middle syllable
        assertFalse(Availability.isValidAvailability("satuday")); // missing middle letter

        // valid availability
        assertTrue(Availability.isValidAvailability("mon")); // first syllable tuesday
        assertTrue(Availability.isValidAvailability("tues")); // first syllable tuesday
        assertTrue(Availability.isValidAvailability("wed")); // first syllable wednesday
        assertTrue(Availability.isValidAvailability("wednes")); // two syllable wednesday
        assertTrue(Availability.isValidAvailability("thu")); // first syllable thursday without rs
        assertTrue(Availability.isValidAvailability("thur")); // first syllable thursday without s
        assertTrue(Availability.isValidAvailability("thurs")); // first syllable thursday with s
        assertTrue(Availability.isValidAvailability("sat")); // first syllable saturday
        assertTrue(Availability.isValidAvailability("satur")); // two syllable saturday
        assertTrue(Availability.isValidAvailability("sun")); // first syllable sunday
        assertTrue(Availability.isValidAvailability("Sun")); // first syllable with capitalised first letter
        assertTrue(Availability.isValidAvailability("sAtUr")); // two syllable mixed case
        assertTrue(Availability.isValidAvailability("monday")); // full word lowercase monday
        assertTrue(Availability.isValidAvailability("tuesday")); // full word lowercase tuesday
        assertTrue(Availability.isValidAvailability("wednesday")); // full word lowercase wednesday
        assertTrue(Availability.isValidAvailability("thursday")); // full word lowercase thursday
        assertTrue(Availability.isValidAvailability("friday")); // full word lowercase friday
        assertTrue(Availability.isValidAvailability("saturday")); // full word lowercase saturday
        assertTrue(Availability.isValidAvailability("sunday")); // full word lowercase sunday
        assertTrue(Availability.isValidAvailability("MONDAY")); // full word uppercase monday
        assertTrue(Availability.isValidAvailability("Tuesday")); // full word with capitalised first letter
        assertTrue(Availability.isValidAvailability("wEdNeSdAy")); // full word mixed case
    }

    @Test
    public void equals() {
        Availability monAvailability = new Availability("monday");

        // same values -> returns true
        assertTrue(monAvailability.equals(new Availability("monday")));

        // same day -> return true
        assertTrue(monAvailability.equals(new Availability("mOn")));

        // same object -> returns true
        assertTrue(monAvailability.equals(monAvailability));

        // null -> returns false
        assertFalse(monAvailability.equals(null));

        // different types -> returns false
        assertFalse(monAvailability.equals(5.0f));

        // different values -> returns false, mon and tues
        assertFalse(monAvailability.equals(new Availability("tues")));

    }

    @Test
    public void strToDayOfWeekTest() {
        assertEquals(DayOfWeek.MONDAY.toString(), Availability.strToDayOfWeek(new String("mon")));
        assertEquals(DayOfWeek.TUESDAY.toString(), Availability.strToDayOfWeek(new String("tues")));
        assertEquals(DayOfWeek.WEDNESDAY.toString(), Availability.strToDayOfWeek(new String("wednes")));
        assertEquals(DayOfWeek.THURSDAY.toString(), Availability.strToDayOfWeek(new String("thursday")));
        assertEquals(DayOfWeek.FRIDAY.toString(), Availability.strToDayOfWeek(new String("FRIDAY")));
        assertEquals(DayOfWeek.SATURDAY.toString(), Availability.strToDayOfWeek(new String("SATurday")));
        assertEquals(DayOfWeek.SUNDAY.toString(), Availability.strToDayOfWeek(new String("sunDAY")));
    }

}
