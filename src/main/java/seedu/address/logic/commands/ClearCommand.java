package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.TaskMasterPro;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.Task;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "TaskMasterPro has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setTaskMasterPro(new TaskMasterPro());
        Employee.setUniversalEmployeeId(1);
        Task.setUniversalTaskId(1);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
