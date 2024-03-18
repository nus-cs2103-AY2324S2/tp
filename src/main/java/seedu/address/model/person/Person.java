package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * Every field must be present and not null.
     */
    public Person(Name name, List<Task> taskList) {
        requireAllNonNull(name);
        this.name = name;
        this.taskList = taskList;
    }

    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
        List<Task> taskList = new ArrayList<>();
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task){
        int i = 0;
        for (Task t : taskList){
            if (t.equals(task)){
                taskList.remove(i);
                break;
            }
            i+=1;
        }
    }

    public Name getName() {
        return name;
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

}
