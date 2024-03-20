package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASK_ALICE = new TaskBuilder().withTitle("Complete Project Proposal")
            .withDeadline("25-03-2024 2359").withPerson(TypicalPersons.ALICE).build();

    public static final Task TASK_BENSON = new TaskBuilder().withTitle("Prepare Presentation Slides")
            .withDeadline("05-05-2024 0900").withPerson(TypicalPersons.BENSON).build();

    public static final Task TASK_CARL = new TaskBuilder().withTitle("Submit Year End Report")
            .withDeadline("30-11-2024 1700").withPerson(TypicalPersons.CARL).build();

    public static final Task TASK_DANIEL = new TaskBuilder().withTitle("Organize Team Meeting")
            .withDeadline("04-08-2024 1400").withPerson(TypicalPersons.DANIEL).build();


    private TypicalTasks() {} // prevents instantiation
    public static List<Task> getTypicalTasks() {
        System.out.println(new ArrayList<>(Arrays.asList(TASK_ALICE, TASK_BENSON, TASK_CARL, TASK_DANIEL)));
        return new ArrayList<>(Arrays.asList(TASK_ALICE, TASK_BENSON, TASK_CARL, TASK_DANIEL));
    }
}
