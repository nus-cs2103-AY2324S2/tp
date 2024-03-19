package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private static int universalEmployeeId = 1;
    private final EmployeeId employeeId;
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final AssignedTasks tasks;

    private final Set<Tag> tags = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Employee(EmployeeId employeeId, Name name,
                    Phone phone, Email email, Address address, AssignedTasks tasks, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags, tasks);
        this.employeeId = employeeId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tasks = tasks;
        this.tags.addAll(tags);
    }

    /**
     * Updates Employee.EMPLOYEE_ID when loading from JSON
     */
    public static void setUniversalEmployeeId(int id) {
        Employee.universalEmployeeId = id;
    }

    public static int getUniversalId() {
        int ret = Employee.universalEmployeeId;
        return ret;
    }

    /**
     * Increments Employee.EMPLOYEE_ID
     */
    public static void incrementEmployeeId() {
        Employee.universalEmployeeId++;
    }

    public EmployeeId getEmployeeId() {
        return employeeId;
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

    public AssignedTasks getTasks() {
        return tasks;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Assigns a task to the employee by updating the employee's task list with the given task ID.
     *
     * @param taskID The ID of the task to be assigned to the employee.
     * @return The updated Employee object with the assigned task.
     * @throws CommandException If the task ID is already present in the employee's task list.
     */
    public Employee assignTask(int taskID) throws CommandException {
        return new Employee(
                employeeId, name, phone, email, address, tasks.updateTask(taskID), tags);
    }

    /**
     * Removes a task from the employee's assigned tasks.
     *
     * @param taskID the ID of the task to be removed
     * @return a new Employee object with the specified task removed from its assigned tasks
     * @throws CommandException if the specified task ID is invalid or the task cannot be removed
     */
    public Employee removeTask(int taskID) throws CommandException {
        return new Employee(
                employeeId, name, phone, email, address, tasks.deleteTask(taskID), tags);
    }

    /**
     * Returns true if both employees have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName());
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return name.equals(otherEmployee.name)
                && phone.equals(otherEmployee.phone)
                && email.equals(otherEmployee.email)
                && address.equals(otherEmployee.address);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(employeeId, name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("employeeId", employeeId)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tasks", tasks)
                .add("tags", tags)
                .toString();
    }
}
