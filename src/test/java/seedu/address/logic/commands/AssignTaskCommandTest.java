package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.AssignedEmployees;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;
import seedu.address.testutil.EmployeeBuilder;

public class AssignTaskCommandTest {
    @Test
    public void test() throws CommandException {
        Employee validEmployee = new EmployeeBuilder().build();
        Task task = new Task(new TaskName("play game"), new TaskId(5), new TaskStatus(false),
                new AssignedEmployees(""));

        Model model = new ModelManager();

        model.addEmployee(validEmployee);
        model.addTask(task);

        AssignTaskCommand atc = new AssignTaskCommand(5, 1);
        atc.execute(model);

        assertEquals("5", model.getFilteredEmployeeList().get(0).getTasks().getTasks());
    }
}
