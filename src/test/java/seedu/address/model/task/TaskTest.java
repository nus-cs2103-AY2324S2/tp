package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void constructor_validInput_success() {
        Task task = new TaskBuilder().withTaskName("Test").withTaskDescription("Test").build();
        assertEquals("Test", task.getName().taskName);
        assertEquals("Test", task.getDescription().taskDescription);
        assertFalse(task.getStatus().getTaskStatus());
    }

    @Test
    public void equals_sameObject_true() {
        Task task = new TaskBuilder().build();
        assertTrue(task.equals(task));
    }

    @Test
    public void equals_differentObjectSameName_true() {
        Task task1 = new TaskBuilder().build();
        Task task2 = new TaskBuilder().build();
        assertTrue(task1.equals(task2));
    }

    @Test
    public void equals_differentObjectDifferentName_false() {
        Task task1 = new TaskBuilder().withTaskName("Test Task 1").build();
        Task task2 = new TaskBuilder().withTaskName("Test Task 2").build();
        assertFalse(task1.equals(task2));
    }

    @Test
    public void equals_differentObjectSameDescription_true() {
        Task task1 = new TaskBuilder().build();
        Task task2 = new TaskBuilder().build();
        assertTrue(task1.equals(task2));
    }

    @Test
    public void equals_differentObjectDifferentDescription_false() {
        Task task1 = new TaskBuilder().withTaskDescription("Test Task 1").build();
        Task task2 = new TaskBuilder().withTaskDescription("Test Task 2").build();
        assertFalse(task1.equals(task2));
    }

    @Test
    public void equals_differentObjectDifferentType_false() {
        Task task = new TaskBuilder().build();
        assertFalse(task.equals(new Object()));
    }

    @Test
    public void setAsDone_checkMarkStatus_true() {
        Task task = new TaskBuilder().build();
        task.getStatus().setAsDone();
        assertTrue(task.getStatus().getTaskStatus());
    }

    @Test
    public void setAsUndone_checkUnmarkStatus_false() {
        Task task = new TaskBuilder().build();
        task.getStatus().setAsUndone();
        assertFalse(task.getStatus().getTaskStatus());
    }

    @Test
    public void compare_differentName() {
        Task task1 = new TaskBuilder().withTaskName("Task 1").build();
        Task task2 = new TaskBuilder().withTaskName("Task 2").build();
        assertEquals(-1, task1.compare(task2));
    }

    @Test
    public void compare_differentDescription() {
        Task task1 = new TaskBuilder().withTaskDescription("Task 1").build();
        Task task2 = new TaskBuilder().withTaskDescription("Task 2").build();
        assertEquals(-1, task1.compare(task2));
    }
}
