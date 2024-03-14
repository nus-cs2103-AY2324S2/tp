package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;

public class TaskListTest {

    private TaskList taskList;
    private Task task1;
    private Task task2;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        task1 = new Task("Task 1");
        task2 = new Task("Task 2");
    }

    @Test
    public void addTask_success() {
        taskList.addTask(task1);
        assertTrue(taskList.hasTask(task1));
    }

    @Test
    public void hasTask_success() {
        taskList.addTask(task1);
        assertTrue(taskList.hasTask(task1));
    }

    @Test
    public void hasTask_failure() {
        assertFalse(taskList.hasTask(task1));
    }

    @Test
    public void getSerializeTaskList_success() {
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.getSerializeTaskList().size());
    }
}
