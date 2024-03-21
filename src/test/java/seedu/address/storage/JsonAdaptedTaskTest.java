package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

public class JsonAdaptedTaskTest {

    private static final String VALID_NAME = "Valid Task Name";
    private static final String EMPTY_NAME = "";
    private static final String VALID_DESCRIPTION = "Valid Task Description";
    private static final String EMPTY_DESCRIPTION = "";
    private static final Task VALID_TASK = new Task(new TaskName(VALID_NAME), new TaskDescription(VALID_DESCRIPTION));
    private static final Task INVALID_TASK = new Task(new TaskName(EMPTY_NAME), new TaskDescription(EMPTY_DESCRIPTION));

    @Test
    public void toModelType_validTask_success() throws IllegalValueException {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_TASK);
        Task modelTask = jsonAdaptedTask.toModelType();
        assertEquals(VALID_TASK, modelTask);
    }

    @Test
    public void toModelType_emptyTask_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(INVALID_TASK);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }
}
