package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;

/**
 * Unassigns a task from an employee.
 */
public class UnassignTaskCommand extends Command {
    public static final String COMMAND_WORD = "unassigntask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unassigns a task object identified by TASK_ID from employee identified by EMPLOYEE_ID.\n"
            + "Parameters: taskID, employeeID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_UNASSIGN_TASK_SUCCESS = "Unassign task success";

    private final int taskID;
    private final int employeeID;

    /**
     * Creates an UnassignTaskCommand to unassign the specified task from the specified employee.
     *
     * @param taskID     ID of the task to unassign
     * @param employeeID ID of the employee from whom the task is to be unassigned
     */
    public UnassignTaskCommand(int taskID, int employeeID) {
        this.taskID = taskID;
        this.employeeID = employeeID;
    }

    /**
     * Executes the command to unassign the task from the employee.
     *
     * @param model the current model
     * @return the command result indicating the success of the operation
     * @throws CommandException if the command cannot be executed
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Employee> employeeList = model.getFilteredEmployeeList();

        boolean checkEmployeeID = false;

        for (Employee employee : employeeList) {
            int id = employee.getEmployeeId().employeeId;

            if (id == employeeID) {
                checkEmployeeID = true;
                Employee updatedEmployee = employee.removeTask(taskID);
                model.setEmployee(employee, updatedEmployee);
            }
        }

        if (!checkEmployeeID) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEEID);
        }

        return new CommandResult(MESSAGE_UNASSIGN_TASK_SUCCESS);
    }
}
