package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
public class TaskListTest {
    @Test
    public void toStringTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.toString(), "1. Submit Supporting Documents\n2. Finish Resume\n");
    }

    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTaskListSize(), 2);
    }

    @Test
    public void JSONConstructorTest() {
        TaskList taskList = new TaskList("[{\"task\": \"Submit Supporting Documents\", \"deadline\": \"\"},"
                + " {\"task\": \"Finish Resume\", \"deadline\": \"\"}]");
        assertEquals(taskList.getTask(1), new Task("Submit Supporting Documents"));
        assertEquals(taskList.getTask(2), new Task("Finish Resume"));
    }

    @Test
    public void getTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTask(1), new Task("Submit Supporting Documents"));
        assertEquals(taskList.getTask(2), new Task("Finish Resume"));
    }

    @Test
    public void getTaskListSizeTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTaskListSize(), 2);
    }

    @Test
    public void equals() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));

        // same values -> returns true
        assertTrue(taskList.equals(new TaskList("[{\"task\": \"Submit Supporting Documents\", \"deadline\":"
                + " \"\"}, {\"task\": \"Finish Resume\", \"deadline\": \"\"}]")));

        // same object -> returns true
        assertTrue(taskList.equals(taskList));

        // null -> returns false
        assertFalse(taskList.equals(null));

        // different types -> returns false
        assertFalse(taskList.equals(5.0f));

        // different values -> returns false
        assertFalse(taskList.equals(new TaskList("[{\"task\": \"Submit Supporting Documents\", \"deadline\": \"\"},"
                + " {\"task\": \"Complete Behavioural Assessment Form\", \"deadline\": \"\"}]")));
    }
}
