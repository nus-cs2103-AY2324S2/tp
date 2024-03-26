package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Wraps all data at the TaskMasterPro level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class TaskMasterPro implements ReadOnlyTaskMasterPro {

    private final UniqueEmployeeList employees;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        employees = new UniqueEmployeeList();
    }

    private final TaskList tasks = new TaskList();

    public TaskMasterPro() {}

    /**
     * Creates an TaskMasterPro using the Employees in the {@code toBeCopied}
     */
    public TaskMasterPro(ReadOnlyTaskMasterPro toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Employee list with {@code Employees}.
     * {@code Employees} must not contain duplicate Employees.
     */
    public void setEmployees(List<Employee> employees) {
        this.employees.setEmployees(employees);
    }

    /**
     * Replaces the contents of the task list with {@code tasks}.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskMasterPro} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskMasterPro newData) {
        requireNonNull(newData);

        setEmployees(newData.getEmployeeList());
        setTasks(newData.getTaskList());
    }

    //// Employee-level operations

    /**
     * Returns true if an Employee with the same identity as {@code employee} exists in TaskMasterPro.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Adds an Employee to TaskMasterPro.
     * The Employee must not already exist in TaskMasterPro.
     */
    public void addEmployee(Employee p) {
        employees.add(p);
    }

    /**
     * Replaces the given Employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in TaskMasterPro.
     * The Employee identity of {@code editedEmployee} must not be the same as
     * another existing Employee in TaskMasterPro.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code TaskMasterPro}.
     * {@code key} must exist in TaskMasterPro.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
    }

    /**
     * Adds an Employee to TaskMasterPro.
     * The Employee must not already exist in TaskMasterPro.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes {@code key} from this {@code TaskMasterPro}.
     * {@code key} must exist in TaskMasterPro.
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    /**
     * Replaces the given Task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in TaskMasterPro.
     * The Task identity of {@code editedTask} must not be the same as
     * another existing Task in TaskMasterPro.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }


    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Employees", employees)
                .toString();
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskMasterPro)) {
            return false;
        }

        TaskMasterPro otherTaskMasterPro = (TaskMasterPro) other;
        return employees.equals(otherTaskMasterPro.employees);
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }


}
