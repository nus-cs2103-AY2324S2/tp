package staffconnect.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import staffconnect.model.person.Name;

class DescriptionTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null Description
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid Description
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter meeting*")); // contains non-alphanumeric characters

        // valid Description
        assertTrue(Name.isValidName("meeting")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("meeting at for 2nd finals")); // alphanumeric characters
        assertTrue(Name.isValidName("Crush the exam")); // with capital letters
        assertTrue(Name.isValidName("Super hard midterm with finals and project combined 2nd")); // long names
    }

    @Test
    public void equals() {
        Description description = new Description("Valid Description");
        MeetDateTime testDate = new MeetDateTime("12/04/2023 12:00");

        // same values -> returns true
        assertEquals(description, new Description("Valid Description"));

        // same object -> returns true
        assertEquals(description, description);

        // null -> returns false
        assertNotEquals(null, description);

        // different types -> returns false
        assertNotEquals(5.0f, description);

        //Different object type -> returns false
        assertFalse(description.equals(testDate));

        // different values -> returns false
        assertNotEquals(description, new Name("Other valid description"));
    }
    @Test
    public void asSymmetricHashcode() {
        Description first = new Description("Valid Description");
        Description second = new Description("Valid Description");
        assertEquals(first.hashCode(), second.hashCode());
    }
}
