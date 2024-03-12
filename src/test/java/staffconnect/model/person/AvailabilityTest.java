package staffconnect.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AvailabilityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidAvailability = "";
        assertThrows(IllegalArgumentException.class, () -> new Availability(invalidAvailability));
    }

    @Test
    public void isValidAvailability() {
        // null module code
        assertThrows(NullPointerException.class, () -> Availability.isValidAvailability(null));

        // invalid module code
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
        assertFalse(Availability.isValidAvailability("thu")); // uncompleted syllable

        // valid module code
        assertTrue(Availability.isValidAvailability("tues")); // first syllable tuesday
        assertTrue(Availability.isValidAvailability("wed")); // first syllable wednesday
        assertTrue(Availability.isValidAvailability("wednes")); // two syllable wednesday
        assertTrue(Availability.isValidAvailability("thur")); // first syllable thursday without s
        assertTrue(Availability.isValidAvailability("thurs")); // first syllable thursday with s
        assertTrue(Availability.isValidAvailability("fri")); // first syllable friday
        assertTrue(Availability.isValidAvailability("sat")); // first syllable saturday
        assertTrue(Availability.isValidAvailability("sun")); // first syllable sunday
        assertTrue(Availability.isValidAvailability("monday")); // full word monday
        assertTrue(Availability.isValidAvailability("tuesday")); // full word tuesday
        assertTrue(Availability.isValidAvailability("wednesday")); // full word wednesday
        assertTrue(Availability.isValidAvailability("thursday")); // full word thursday
        assertTrue(Availability.isValidAvailability("friday")); // full word friday
        assertTrue(Availability.isValidAvailability("saturday")); // full word saturday
        assertTrue(Availability.isValidAvailability("sunday")); // full word sunday
    }

    @Test
    public void equals() {
        Availability availability = new Availability("monday");

        // same values -> returns true
        assertTrue(availability.equals(new Availability("monday")));

        // same day -> return true
        assertTrue(availability.equals(new Availability("mon")));

        // same object -> returns true
        assertTrue(availability.equals(availability));

        // null -> returns false
        assertFalse(availability.equals(null));

        // different types -> returns false
        assertFalse(availability.equals(5.0f));

        // different values -> returns false
        assertFalse(availability.equals(new Availability("sunday")));
    }
}
