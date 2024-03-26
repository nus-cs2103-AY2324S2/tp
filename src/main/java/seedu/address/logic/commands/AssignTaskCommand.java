package seedu.address.logic.commands;

import java.util.List;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.Task;

/**
 * Assigns a task to an employee.
 */
public class AssignTaskCommand extends Command {
    public static final String COMMAND_WORD = "assigntask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns a task object identified by TASK_ID to employee identified by EMPLOYEE_ID.\n"
            + "Parameters: taskID, employeeID (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Assign task success";

    private final int taskID;
    private final int employeeID;

    /**
     * Creates an AssignTaskCommand to assign a task to an employee.
     * @param taskID The ID of the task to be assigned.
     * @param employeeID The ID of the employee to whom the task is assigned.
     */
    public AssignTaskCommand(int taskID, int employeeID) {
        this.taskID = taskID;
        this.employeeID = employeeID;
    }

    /**
     * Executes the AssignTaskCommand to assign a task to an employee.
     * @param model The model in which the command should be executed.
     * @return The result of the command execution.
     * @throws CommandException If there is an error executing the command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> taskList = model.getFilteredTaskList();
        List<Employee> employeeList = model.getFilteredEmployeeList();

        boolean checkTaskID = false;
        boolean checkEmployeeID = false;

        for (Task t : taskList) {
            if (t.getTaskId().taskId == taskID) {
                checkTaskID = true;
                for (Employee e : employeeList) {
                    if (e.getEmployeeId().employeeId == employeeID) {
                        checkEmployeeID = true;
                        Employee updatedEmployee = e.assignTask(t);
                        model.setEmployee(e, updatedEmployee);

                        Task updatedTask = t.assignEmployee(e);
                        model.setTask(t, updatedTask);
                    }
                }
            }
        }

        if (!checkTaskID) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASKID);
        }

        if (!checkEmployeeID) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEEID);
        }
        return new CommandResult(MESSAGE_ASSIGN_TASK_SUCCESS);
    }
}
