package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * A utility class containing  objects to be used in tests.
 */
public class TypicalAddressBook {

    /**
     * Returns an {@code AddressBook} with all the typical persons and tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }
        for (Task task : TypicalTasks.getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }
}
