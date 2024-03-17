package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedEmployee.MISSING_FIELD_MESSAGE_FORMAT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskName;
    private final int taskId;

    private boolean taskStatus;

    /**
     * Constructs a {@code JsonAdaptedEmployee} with the given Employee details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("taskId") int taskId,
                           @JsonProperty("taskStatus") boolean taskStatus) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.taskStatus = taskStatus;
    }


    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName().taskName;
        taskId = source.getTaskId().taskId;
        taskStatus = source.getTaskStatus().getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted Employee object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Employee.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }

        final TaskName modelName = new TaskName(taskName);
        final TaskId modelId = new TaskId(taskId);
        final TaskStatus modelStatus = new TaskStatus(taskStatus);
        return new Task(modelName, modelId, modelStatus);
    }
}
