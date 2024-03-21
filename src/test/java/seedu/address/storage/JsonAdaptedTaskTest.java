package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class JsonAdaptedTaskTest {

    private static final String VALID_NAME = "Valid Task Name";
    private static final String EMPTY_NAME = "";
    private static final String VALID_DESCRIPTION = "Valid Task Description";
    private static final String EMPTY_DESCRIPTION = "";
    private static final String STATUS = "Not Done";
    private static final Task VALID_TASK = new TaskBuilder().withTaskName(VALID_NAME)
            .withTaskDescription(VALID_DESCRIPTION).build();

    @Test
    public void toModelType_validTask_success() throws IllegalValueException {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, STATUS);
        Task modelTask = jsonAdaptedTask.toModelType();
        assertEquals(VALID_TASK, modelTask);
    }

    @Test
    public void toModelType_emptyName_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(EMPTY_NAME, VALID_DESCRIPTION, STATUS);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }

    @Test
    public void toModelType_emptyDescription_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_NAME, EMPTY_DESCRIPTION, STATUS);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(null, VALID_DESCRIPTION, STATUS);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_NAME, null, STATUS);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask jsonAdaptedTask = new JsonAdaptedTask(VALID_NAME, VALID_DESCRIPTION, null);
        assertThrows(IllegalValueException.class, jsonAdaptedTask::toModelType);
    }
}
