package seedu.address.model.task;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * Represents a Task in the address book.
 */
public class Task {
    // Identity fields
    private static int universalTaskId = 1;
    private final TaskName taskName;
    private final TaskId taskId;
    private final TaskStatus taskStatus;
    private final AssignedEmployees employees;

    /**
     * Every field must be present and not null.
     */
    public Task(TaskName name, TaskId id, TaskStatus status, AssignedEmployees employees) {
        taskName = name;
        taskId = id;
        taskStatus = status;
        this.employees = employees;
    }

    /**
     * Updates Task.TASK_ID when loading from JSON
     */
    public static void setUniversalTaskId(int id) {
        Task.universalTaskId = id;
    }

    public static int getUniversalId() {
        int ret = Task.universalTaskId;
        return ret;
    }
    /**
     * Increments Task.TASK_ID
     */
    public static void incrementTaskId() {
        Task.universalTaskId++;
    }

    public TaskName getName() {
        return taskName;
    }

    public TaskId getTaskId() {
        return taskId;
    }
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    public AssignedEmployees getEmployees() {
        return employees;
    }

    public void markTask() {
        taskStatus.setTaskDone();
    }

    public void unmarkTask() {
        taskStatus.setTaskNotDone();
    }

    /**
     * Assigns an employee to the task by updating the task's employee list with the given employee ID.
     *
     * @param employee The employee to be assigned to the task.
     * @return The updated Task object with the assigned employee.
     * @throws CommandException If the employee ID is already present in the task's employee list.
     */
    public Task assignEmployee(Employee employee) throws CommandException {
        AssignedEmployees updatedEmployees = employees.assignEmployee(employee);
        return new Task(
                taskName, taskId, taskStatus, updatedEmployees);
    }

    /**
     * Removes an employee from the task's assigned employees.
     *
     * @param employeeId the ID of the employee to be removed
     * @return a new Task object with the specified employee removed from its assigned employees
     * @throws CommandException if the specified employee ID is invalid or the employee cannot be removed
     */
    public Task removeEmployee(EmployeeId employeeId) throws CommandException {
        AssignedEmployees updatedEmployees = employees.unassignEmployee(employeeId);
        return new Task(
                taskName, taskId, taskStatus, updatedEmployees);
    }

    /**
     * Removes this task from all assigned employees.
     * This command will be run before this task is deleted.
     */
    public void removeAssignments() {
        for (EmployeeId employeeId : employees.getAssignedEmployees().keySet()) {
            // Retrieve the Employee using the key
            Employee employee = employees.getAssignedEmployees().get(employeeId);

            // Remove this task from the employee
            try {
                employee.removeTask(taskId);
            } catch (CommandException e) {
                //Ignore this exception as this error is not important since the Task is being deleted
            }
        }
    }

    /**
     * Returns true if both tasks have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("taskId", taskId)
                .add("taskName", taskName)
                .add("taskStatus", taskStatus)
                .add("employees", employees)
                .toString();
    }
}
