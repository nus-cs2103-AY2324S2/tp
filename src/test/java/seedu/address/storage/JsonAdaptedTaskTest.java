package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;

public class JsonAdaptedTaskTest {

    private static final String VALID_DESCRIPTION = "Valid Task Description";
    private static final String EMPTY_DESCRIPTION = "";

    @Test
    public void toModelType_validDescription_success() throws IllegalValueException {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_DESCRIPTION);
        Task modelTask = jsonAdaptedTask.toModelType();
        assertEquals(VALID_DESCRIPTION, modelTask.getDescription());
    }

    @Test
    public void toModelType_emptyDescription_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(EMPTY_DESCRIPTION);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }

    @Test
    public void toModelType_fromTask_success() throws IllegalValueException {
        Task task = new Task(VALID_DESCRIPTION);
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(task);
        Task modelTask = jsonAdaptedTask.toModelType();
        assertEquals(task.getDescription(), modelTask.getDescription());
    }
}
