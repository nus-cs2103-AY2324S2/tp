package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void testTask() {
        Task validTask = new Task(new TaskName("Test"), new TaskId(2));
        assertTrue(validTask.getName().taskName.equals("Test"));
        Task.setUniversalTaskId(100);
        assertTrue(validTask.getTaskId().taskId == 2);
        Task.incrementTaskId();
        Task validTask2 = new Task(new TaskName("Test"), new TaskId(Task.getUniversalId()));
        assertTrue(validTask2.getTaskId().taskId == 101);
    }
}
