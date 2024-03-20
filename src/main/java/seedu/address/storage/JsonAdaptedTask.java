package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;


/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    //private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");
    private final String title;
    private final LocalDateTime deadline;
    private final String personInCharge;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("deadline") String deadline,
                           @JsonProperty("personInCharge") String personInCharge) {
        this.title = title;
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        this.personInCharge = personInCharge;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTaskTitle();
        deadline = source.getDeadline().dateTime;
        personInCharge = source.getPersonInCharge().getName().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType(AddressBook ab) throws IllegalValueException {
        if (title == null || title == "") {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "task title"));
        }
        final String modelTaskTitle = title;

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "deadline"));
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (personInCharge == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "personInCharge"));
        }
        if (!Name.isValidName(personInCharge)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name name = new Name(personInCharge);

        // Find the Person object from the personList using the personName
        final Person modelPic = ab.getPerson(name);

        if (modelPic == null) {
            throw new IllegalValueException("Person with name " + personInCharge + " not found!");
        }

        Task task = new Task(modelTaskTitle, modelDeadline);
        ab.assignTask(task, modelPic);
        return task;
    }
}
