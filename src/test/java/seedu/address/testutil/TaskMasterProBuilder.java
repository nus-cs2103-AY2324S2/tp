package seedu.address.testutil;

import seedu.address.model.TaskMasterPro;
import seedu.address.model.employee.Employee;

/**
 * A utility class to help with building TaskMasterPro objects.
 * Example usage: <br>
 *     {@code TaskMasterPro ab = new TaskMasterProBuilder().withEmployee("John", "Doe").build();}
 */
public class TaskMasterProBuilder {

    private TaskMasterPro taskMasterPro;

    public TaskMasterProBuilder() {
        taskMasterPro = new TaskMasterPro();
    }

    public TaskMasterProBuilder(TaskMasterPro taskMasterPro) {
        this.taskMasterPro = taskMasterPro;
    }

    /**
     * Adds a new {@code Employee} to the {@code TaskMasterPro} that we are building.
     */
    public TaskMasterProBuilder withEmployee(Employee employee) {
        taskMasterPro.addEmployee(employee);
        return this;
    }

    public TaskMasterPro build() {
        return taskMasterPro;
    }
}
