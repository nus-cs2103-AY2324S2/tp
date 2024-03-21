package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.TaskList;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getTaskSet(getSampleTasks()[0])),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTaskSet(getSampleTasks()[0], getSampleTasks()[1]))
        };
    }

    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new TaskName("Implement find for task"), new TaskDescription("Find task has to be done")),
            new Task(new TaskName("Implement remind for task"), new TaskDescription("Remind task has to be done"))
        };
    }

    public static TaskList getSampleTaskList() {
        TaskList tasks = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            tasks.addTask(sampleTask);
        }
        return tasks;
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a task set containing the list of strings given.
     */
    public static Set<Task> getTaskSet(Task... tasks) {
        return Arrays.stream(tasks)
                .collect(Collectors.toSet());
    }

}
