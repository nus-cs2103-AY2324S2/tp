package seedu.address.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.Messages.MESSAGE_DUPLICATE_TASKID;
import static seedu.address.logic.Messages.MESSAGE_INVALID_EMPLOYEEID;
import static seedu.address.logic.Messages.MESSAGE_NONEXISTENT_EMPLOYEES;

import java.util.Hashtable;
import java.util.List;
import java.util.Objects;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.UnassignTaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * Represents a Task's AssignedEmployees in TaskMasterPro.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmployee(String)}
 */
public class AssignedEmployees {

    public static final String MESSAGE_CONSTRAINTS =
            "TASK_IDS should only contain numeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum} ]*$";

    private String employees;

    private Hashtable<EmployeeId, Employee> assignedEmployees;

    /**
     * Constructs a {@code AssignedEmployees}.
     *
     * @param employees A valid name.
     */
    public AssignedEmployees(String employees) {
        requireNonNull(employees);
        checkArgument(isValidEmployee(employees), MESSAGE_CONSTRAINTS);
        this.employees = employees;
        this.assignedEmployees = new Hashtable<>();
    }

    /**
     * Gets the employees assigned to the task.
     *
     * @return A string containing the employees assigned to the task.
     */
    public String getEmployees() {
        return employees;
    }

    /**
     * Gets the employees assigned to the task.
     *
     * @return A hashtable containing the employees assigned to the task.
     */
    public Hashtable<EmployeeId, Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEmployee(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * JsonSerializableTaskMasterPro will run this after all Employees and Tasks has been added.
     * The hashtable will be initialized with stored data.
     */
    public void initiateHashTable(List<Employee> employeeList) {
        if (employees.equals("")) {
            return;
        }
        String[] employeeArray = employees.split(" ");

        // Check if employeeID matches any of the numbers in employees
        for (String employeeId : employeeArray) {
            for (Employee employee : employeeList) {
                if (Integer.parseInt(employeeId) == employee.getEmployeeId().employeeId) {
                    assignedEmployees.put(employee.getEmployeeId(), employee);
                }
            }
        }
    }

    /**
     * Adds a new employee into assignedEmployees.
     *
     * @param employee The employee to be added.
     * @return The updated AssignedEmployees object.
     * @throws CommandException If the employee ID is already present in the assigned employees.
     */
    public AssignedEmployees assignEmployee(Employee employee) throws CommandException {
        if (Objects.equals(employees, "")) {
            employees = "" + employee.getEmployeeId().employeeId;
            assignedEmployees.put(employee.getEmployeeId(), employee);
            return this;
        }
        String[] employeeArray = employees.split(" ");

        // Check if employeeID matches any of the numbers in employees
        for (String employeeId : employeeArray) {
            if (Integer.parseInt(employeeId) == employee.getEmployeeId().employeeId) {
                throw new CommandException(
                        String.format(MESSAGE_DUPLICATE_TASKID, AssignTaskCommand.MESSAGE_USAGE));
            }
        }

        // Add the taskID to tasks
        employees += " " + employee.getEmployeeId().employeeId;
        employees.trim();

        if (assignedEmployees.get(employee.getEmployeeId()) != null) {
            throw new CommandException(
                    String.format(MESSAGE_DUPLICATE_TASKID, AssignTaskCommand.MESSAGE_USAGE));
        }
        assignedEmployees.put(employee.getEmployeeId(), employee);
        return this;
    }

    /**
     * Removes an employee from assignedEmployees.
     * Deletes the specified employee from the assigned employees list.
     * If the specified employee ID is not found in the assigned employees list,
     * or if the assigned employees list is empty, a CommandException is thrown.
     *
     * @param employeeId The employee to be removed.
     * @return The updated AssignedEmployees object.
     * @throws CommandException If the employee ID is not present in the assigned employees.
     */
    public AssignedEmployees unassignEmployee(EmployeeId employeeId) throws CommandException {
        if (Objects.equals(employees, "")) {
            throw new CommandException(
                    String.format(MESSAGE_NONEXISTENT_EMPLOYEES, UnassignTaskCommand.MESSAGE_USAGE));
        }

        String[] employeeArray = employees.split(" ");
        StringBuilder updatedEmployees = new StringBuilder();

        boolean employeeFound = false;

        for (String employee : employeeArray) {
            if (Integer.parseInt(employee) == employeeId.employeeId) {
                employeeFound = true;
            } else {
                updatedEmployees.append(employee).append(" ");
            }
        }
        if (!employeeFound) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_EMPLOYEEID, UnassignTaskCommand.MESSAGE_USAGE));
        }
        employees = updatedEmployees.toString().trim();

        if (assignedEmployees.get(employeeId) == null) {
            throw new CommandException(
                    String.format(MESSAGE_INVALID_EMPLOYEEID, AssignTaskCommand.MESSAGE_USAGE));
        }
        assignedEmployees.remove(employeeId);
        return this;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignedEmployees)) {
            return false;
        }

        AssignedEmployees otherAssignedEmployees = (AssignedEmployees) other;
        return employees.equals(otherAssignedEmployees.employees)
                && assignedEmployees.equals((otherAssignedEmployees.assignedEmployees));
    }

    @Override
    public String toString() {
        String employeeString = "";
        for (EmployeeId employeeId : assignedEmployees.keySet()) {
            // Retrieve the Employee using the key
            Employee employee = assignedEmployees.get(employeeId);

            // Append the Employee details to the string
            employeeString += (employee.getEmployeeId().toString() + ". " + employee.getName() + "\n");
        }
        return employeeString;
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}
