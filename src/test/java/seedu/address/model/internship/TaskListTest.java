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
    public void getTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Task("Submit Supporting Documents"));
        taskList.addTask(new Task("Finish Resume"));
        assertEquals(taskList.getTask(0), new Task("Submit Supporting Documents"));
        assertEquals(taskList.getTask(1), new Task("Finish Resume"));
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
        TaskList taskList2 = new TaskList();
        taskList2.addTask(new Task("Submit Supporting Documents"));
        taskList2.addTask(new Task("Finish Resume"));
        assertTrue(taskList2.equals(taskList));

        // same object -> returns true
        assertTrue(taskList.equals(taskList));

        // null -> returns false
        assertFalse(taskList.equals(null));

        // different types -> returns false
        assertFalse(taskList.equals(5.0f));

        // different values -> returns false
        TaskList taskList3 = new TaskList();
        taskList3.addTask(new Task("Submit Supporting Documents"));
        assertFalse(taskList.equals(taskList3));
    }
}
