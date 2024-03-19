package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.task.Task;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Task> tasks = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Task> tasks) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tasks.addAll(tasks);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable task set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Task> getTasks() {
        return Collections.unmodifiableSet(tasks);
    }

    /**
     * @param task to be assigned to {@code this}
     * @return a new {@code Person} as a result of assigning {@code task} to
     *         {@code this}
     */
    public Person addTask(Task task) {
        Set<Task> editedTasks = new HashSet<>(tasks);
        editedTasks.add(task);
        return new Person(name, phone, email, address, editedTasks);
    }

    /**
     * @param task to be unassigned from {@code this}
     * @return a new {@code Person} as a result of unassigning {@code task} to
     *         {@code this}
     */
    public boolean hasTask(Task task) {
        for (Task t : tasks) {
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param task to be unassigned from {@code this}
     * @return a new {@code Person} as a result of unassigning {@code task} to
     *         {@code this}
     */
    public Person deleteTask(Task task) {
        Set<Task> editedTasks = new HashSet<>(tasks);
        editedTasks.remove(task);
        return new Person(name, phone, email, address, editedTasks);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tasks.equals(otherPerson.tasks);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tasks);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tasks", tasks)
                .toString();
    }

}
