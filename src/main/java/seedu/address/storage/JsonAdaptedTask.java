package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;


/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String title;
    private final String deadline;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTaskTitle();
        deadline = source.getDeadline().dateTime.toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (title == null || title == "") {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "task title"));
        }
        final String modelTaskTitle = title;

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "deadline"));
        }
        LocalDateTime parsedDeadline = LocalDateTime.parse(deadline);
        final Deadline modelDeadline = new Deadline(parsedDeadline);

        return new Task(modelTaskTitle, modelDeadline);
    }
}
