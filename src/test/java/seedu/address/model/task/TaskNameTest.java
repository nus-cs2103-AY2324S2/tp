package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        String invalidTaskName = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidTaskName));
    }

    @Test
    public void isValidTaskName() {
        // null name
        assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid name
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only

        // valid name
        assertTrue(TaskName.isValidTaskName("peter jack")); // alphabets only
        assertTrue(TaskName.isValidTaskName("12345")); // numbers only
        assertTrue(TaskName.isValidTaskName("peter the 2nd")); // alphanumeric characters
        assertTrue(TaskName.isValidTaskName("Capital Tan")); // with capital letters
        assertTrue(TaskName.isValidTaskName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void toStringTest() {
        TaskName taskName = new TaskName("Valid Name");
        assertEquals("Valid Name", taskName.toString());
    }

    @Test
    public void equals() {
        TaskName taskName = new TaskName("Valid Name");

        // same values -> returns true
        assertTrue(taskName.equals(new TaskName("Valid Name")));

        // same object -> returns true
        assertTrue(taskName.equals(taskName));

        // null -> returns false
        assertFalse(taskName.equals(null));

        // different types -> returns false
        assertFalse(taskName.equals(5.0f));

        // different values -> returns false
        assertFalse(taskName.equals(new TaskName("Other Valid Name")));
    }

    @Test
    public void hashCodeTest() {
        TaskName taskName = new TaskName("Valid Name");

        assertEquals(taskName.hashCode(), taskName.taskName.hashCode());
    }
}
