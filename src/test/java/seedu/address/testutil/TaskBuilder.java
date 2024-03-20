package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.model.person.Person;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {
    public static final String DEFAULT_TITLE = "Submit Meeting Report";
    public static final String DEFAULT_DEADLINE = "31-12-2024 2359";
    public static final Person DEFAULT_PERSON_IN_CHARGE = TypicalPersons.AMY;

    private String title;
    private Deadline deadline;
    private Person personInCharge;

    /**
     * Creates a {@code TaskBuilder} with the default details.
     */
    public TaskBuilder() {
        title = DEFAULT_TITLE;
        deadline = new Deadline(LocalDateTime.parse(DEFAULT_DEADLINE, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        title = taskToCopy.getTaskTitle();
        deadline = taskToCopy.getDeadline();
        personInCharge = taskToCopy.getPersonInCharge();
    }

    public Task buildTester() {
        return new Task(DEFAULT_TITLE, deadline);
    }

    /**
     * Sets the {@code Title} of the {@code Task} that we are building.
     */
    public TaskBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Parses the deadline of the {@code Task} that we are building.
     */
    public TaskBuilder withDeadline(String deadline) throws DateTimeParseException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime parsedDeadline = LocalDateTime.parse(deadline, f);
        this.deadline = new Deadline(parsedDeadline);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Task} that we are building.
     */
    public TaskBuilder withPerson(Person pic) {
        this.personInCharge = pic;
        return this;
    }

    /**
     * Builds a Task with the given details.
     */
    public Task build() {
        Task t = new Task(title, deadline);
        t.setPersonInCharge(personInCharge);
        personInCharge.setTask(t);
        return t;
    }

}
