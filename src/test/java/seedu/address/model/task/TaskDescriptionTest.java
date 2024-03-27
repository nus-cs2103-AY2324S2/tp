package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskDescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidTaskDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidTaskDescription));
    }

    @Test
    public void isValidTaskDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> TaskDescription.isValidTaskDescription(null));

        // invalid description
        assertFalse(TaskDescription.isValidTaskDescription("")); // empty string
        assertFalse(TaskDescription.isValidTaskDescription(" ")); // spaces only

        // valid description
        assertTrue(TaskDescription.isValidTaskDescription("peter jack")); // alphabets only
        assertTrue(TaskDescription.isValidTaskDescription("12345")); // numbers only
        assertTrue(TaskDescription.isValidTaskDescription("peter the 2nd")); // alphanumeric characters
        assertTrue(TaskDescription.isValidTaskDescription("Capital Tan")); // with capital letters
        assertTrue(TaskDescription.isValidTaskDescription("David Roger Jackson Ray Jr 2nd")); // long description
    }

    @Test
    public void toStringTest() {
        TaskDescription taskDescription = new TaskDescription("Valid Description");
        assertEquals("Valid Description", taskDescription.toString());
    }

    @Test
    public void equals() {
        TaskDescription taskDescription = new TaskDescription("Valid Description");

        // same values -> returns true
        assertTrue(taskDescription.equals(new TaskDescription("Valid Description")));

        // same object -> returns true
        assertTrue(taskDescription.equals(taskDescription));

        // null -> returns false
        assertFalse(taskDescription.equals(null));

        // different types -> returns false
        assertFalse(taskDescription.equals(5.0f));

        // different values -> returns false
        assertFalse(taskDescription.equals(new TaskDescription("Other Valid Description")));
    }

    @Test
    public void hashCodeTest() {
        TaskDescription taskDescription = new TaskDescription("Valid Description");

        assertEquals(taskDescription.hashCode(), taskDescription.taskDescription.hashCode());
    }
}
