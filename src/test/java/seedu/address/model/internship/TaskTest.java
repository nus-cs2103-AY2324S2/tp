package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class TaskTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Task(null));
    }

    @Test
    public void constructor_invalidTask_throwsIllegalArgumentException() {
        String invalidTask = "";
        assertThrows(IllegalArgumentException.class, () -> new Task(invalidTask));
    }

    @Test
    public void isValidTask() {
        // null tasks
        assertThrows(NullPointerException.class, () -> Task.isValidTask(null));

        // invalid tasks
        assertFalse(Task.isValidTask(""));
        assertFalse(Task.isValidTask(" "));

        // valid tasks
        assertTrue(Task.isValidTask("Submit Supporting Documents"));
    }

    @Test
    public void equals() {
        Task task = new Task("Submit Supporting Documents");

        // same values -> returns true
        assertTrue(task.equals(new Task("Submit Supporting Documents")));

        // same object -> returns true
        assertTrue(task.equals(task));

        // null -> returns false
        assertFalse(task.equals(null));

        // different types -> returns false
        assertFalse(task.equals(5.0f));

        // different values -> returns false
        assertFalse(task.equals(new Task("Complete Behavioural Assessment Form")));
    }
}
