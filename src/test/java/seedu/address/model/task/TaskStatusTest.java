package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskStatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskStatus(null));
    }

    @Test
    public void constructor_invalidTaskStatus_throwsIllegalArgumentException() {
        String invalidTaskStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskStatus(invalidTaskStatus));
    }

    @Test
    public void constructor_newTaskStatus_assertfalse() {
        assertFalse(new TaskStatus().getTaskStatus());
    }

    @Test
    public void constructor_validTaskStatus_done() {
        assertTrue(new TaskStatus("Done").getTaskStatus());
    }

    @Test
    public void constructor_validTaskStatus_notDone() {
        assertFalse(new TaskStatus("Not Done").getTaskStatus());
    }

    @Test
    public void setAsDone_checkMarkStatus_true() {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setAsDone();
        assertTrue(taskStatus.getTaskStatus());
    }

    @Test
    public void setAsUndone_checkUnmarkStatus_false() {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setAsUndone();
        assertFalse(taskStatus.getTaskStatus());
    }

    @Test
    public void toStringTest_done() {
        TaskStatus taskStatus = new TaskStatus("Done");
        assertEquals("Done", taskStatus.toString());
    }

    @Test
    public void toStringTest_notDone() {
        TaskStatus taskStatus = new TaskStatus();
        assertEquals("Not Done", taskStatus.toString());
    }

    @Test
    public void equals() {
        TaskStatus taskStatus = new TaskStatus();

        // same values -> returns true
        assertTrue(taskStatus.equals(new TaskStatus()));

        // same object -> returns true
        assertTrue(taskStatus.equals(taskStatus));

        // null -> returns false
        assertFalse(taskStatus.equals(null));

        // different types -> returns false
        assertFalse(taskStatus.equals(5.0f));

        // different values -> returns false
        assertFalse(taskStatus.equals(new TaskStatus("Done")));
    }
}
