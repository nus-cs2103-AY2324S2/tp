package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.project.Task;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;

    private final List<Task> taskList;

    /**
     * Constructs a Person object with empty taskList
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
        List<Task> taskList = new ArrayList<>();
        this.taskList = taskList;
    }

    /**
     * Adds task to the Person object
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes task from the Person object
     */
    public void removeTask(Task task) {
        int i = 0;
        for (Task t : taskList) {
            if (t.equals(task)) {
                taskList.remove(i);
                break;
            }
            i += 1;
        }
    }

    /**
     * @param taskName name to be matched with the tasks listed in my project
     * @return task in the project with the matching taskName
     */
    public Task findTask(Name taskName) {
        Optional<Task> foundTask = taskList.stream()
                .filter(task -> task.getName().toString().equals(taskName.toString()))
                .findFirst();
        return foundTask.get();
    }

    public Name getName() {
        return name;
    }

    public List<Task> getDoneTasks() {
        ArrayList<Task> tmp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus() == "Complete") {
                tmp.add(task);
            }
        }
        return tmp;
    }

    public List<Task> getUndoneTasks() {
        ArrayList<Task> tmp = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getStatus() == "Incomplete") {
                tmp.add(task);
            }
        }
        return tmp;
    }

    /**
     * Returns true if both projects have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherProject) {
        if (otherProject == this) {
            return true;
        }

        return otherProject != null
                && otherProject.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name).toString();
    }

    /**
     * Returns true if the Person has a task that is equal to the specified task
     */
    public boolean hasTask(Task task) {
        for (Task t : taskList) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

}
