package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void constructor_validInput_success() {
        Task task = new Task("Test Task");
        assertEquals("Test Task", task.getDescription());
        assertFalse(task.getStatus());
    }

    @Test
    public void equals_sameObject_true() {
        Task task = new Task("Test Task");
        assertTrue(task.equals(task));
    }

    @Test
    public void equals_differentObjectSameDescription_true() {
        Task task1 = new Task("Test Task");
        Task task2 = new Task("Test Task");
        assertTrue(task1.equals(task2));
    }

    @Test
    public void equals_differentObjectDifferentDescription_false() {
        Task task1 = new Task("Test Task 1");
        Task task2 = new Task("Test Task 2");
        assertFalse(task1.equals(task2));
    }

    @Test
    public void equals_differentObjectDifferentType_false() {
        Task task = new Task("Test Task");
        assertFalse(task.equals(new Object()));
    }

    @Test
    public void setAsDone_checkMarkStatus_true() {
        Task task = new Task("Test Task");
        task.setAsDone();
        assertTrue(task.getStatus());
    }

    @Test
    public void setAsUndone_checkUnmarkStatus_false() {
        Task task = new Task("Test Task");
        task.setAsUndone();
        assertFalse(task.getStatus());
    }
}
