package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InterviewTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InterviewTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsDateTimeParseException() {
        String invalidDateTime = "dfbnkasljbjbe";
        String invalidDateTime2 = "140020240402";
        assertThrows(IllegalArgumentException.class, () -> new InterviewTime(invalidDateTime));
        assertThrows(IllegalArgumentException.class, () -> new InterviewTime(invalidDateTime2));
    }

    @Test
    public void equals() {
        InterviewTime dateTime = new InterviewTime("121220221400");

        // same values -> returns true
        assertTrue(dateTime.equals(new InterviewTime("121220221400")));

        // same object -> returns true
        assertTrue(dateTime.equals(dateTime));

        // null -> returns false
        assertFalse(dateTime.equals(null));

        // different types -> returns false
        assertFalse(dateTime.equals(5.0f));

        // different values -> returns false
        assertFalse(dateTime.equals(new Address("111220221400")));
    }
    @Test
    public void parseInterviewTime() {
        InterviewTime dateTime = new InterviewTime("121220221400");
        assertEquals("2022-12-12T14:00", dateTime.getDateTime().toString());
    }

    @Test
    public void hashcode() {
        InterviewTime dateTime = new InterviewTime("121220221400");

        //different values -> different hashcode
        assertFalse(dateTime.hashCode() == new InterviewTime("121120221400").hashCode());
    }
    @Test
    public void toStringTest() {
        InterviewTime dateTime = new InterviewTime("121220221400");
        assertEquals("December 12, 2022 02:00 PM", dateTime.toString());
    }
}
