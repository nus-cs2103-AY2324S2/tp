package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void testTask() {
        Task validTask = new Task(new TaskName("Test"), new TaskId(2));
        assertEquals("Test", validTask.getName().taskName);
        assertEquals(validTask.getTaskId().toString(), "2");
        Task.setUniversalTaskId(100);
        assertEquals(2, validTask.getTaskId().taskId);
        Task.incrementTaskId();
        Task validTask2 = new Task(new TaskName("Test"), new TaskId(Task.getUniversalId()));
        assertEquals(101, validTask2.getTaskId().taskId);
    }

    @Test
    public void test2() {
        Task validTask = new Task(new TaskName("Test"), new TaskId(2));
        Task validTask2 = new Task(new TaskName("Homework"), new TaskId(222));

        assertFalse(validTask.isSameTask(validTask2));
        assertNotEquals(validTask.toString(), validTask2.toString());
    }
}
