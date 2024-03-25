package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskName;
    private final String taskDescription;
    private final String taskStatus;
    private final String taskDeadline;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
            @JsonProperty("taskDescription") String taskDescription,
            @JsonProperty("taskStatus") String taskStatus,
            @JsonProperty("taskDeadline") String taskDeadline) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStatus = taskStatus;
        this.taskDeadline = taskDeadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName().taskName;
        taskDescription = source.getDescription().taskDescription;
        taskStatus = source.getStatus().toString();
        taskDeadline = source.getDeadline().toJsonSave();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelTaskName = new TaskName(taskName);

        if (taskDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDescription.class.getSimpleName()));
        }
        if (!TaskDescription.isValidTaskDescription(taskDescription)) {
            throw new IllegalValueException(TaskDescription.MESSAGE_CONSTRAINTS);
        }
        final TaskDescription modelTaskDescription = new TaskDescription(taskDescription);

        if (taskStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskStatus.class.getSimpleName()));
        }
        final TaskStatus modelTaskStatus = new TaskStatus(taskStatus);

        if (taskDeadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidTaskDeadline(taskDeadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelTaskDeadline = new TaskDeadline(taskDeadline);

        return new Task(modelTaskName, modelTaskDescription, modelTaskStatus, modelTaskDeadline);
    }
}
