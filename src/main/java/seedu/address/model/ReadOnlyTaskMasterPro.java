package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyTaskMasterPro {

    /**
     * Returns an unmodifiable view of the employees list.
     * This list will not contain any duplicate employees.
     */
    ObservableList<Employee> getEmployeeList();

    /**
     * Returns an unmodifiable view of the tasks list.
     */
    ObservableList<Task> getTaskList();
}
