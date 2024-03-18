package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;

public class JsonAdaptedTaskTest {
    @Test
    public void test() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, 123);
        assertThrows(IllegalValueException.class, String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT,
                TaskName.class.getSimpleName()), task::toModelType);
    }

    @Test
    public void test2() {
        JsonAdaptedTask task = new JsonAdaptedTask(new Task(new TaskName(null), new TaskId(123)));
        assertThrows(IllegalValueException.class, String.format(JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT,
                TaskName.class.getSimpleName()), task::toModelType);
    }

    @Test
    public void test3() throws IllegalValueException {
        JsonAdaptedTask task = new JsonAdaptedTask(new Task(new TaskName("Test"), new TaskId(123)));
        Task.setUniversalTaskId(123);
        JsonAdaptedTask task2 = new JsonAdaptedTask(new Task(new TaskName("Test"), new TaskId(Task.getUniversalId())));
        assertEquals(task.toModelType().toString(), task2.toModelType().toString());
    }
}
