package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.employee.Employee;
import seedu.address.model.task.AssignedEmployees;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;
import seedu.address.testutil.EmployeeBuilder;


public class AssignTaskCommandParserTest {
    private AssignTaskCommandParser parser = new AssignTaskCommandParser();
    @Test
    public void test() throws ParseException, CommandException {
        Model model = new ModelManager();
        Employee validEmployee = new EmployeeBuilder().build();

        model.addEmployee(validEmployee); // id of 1
        model.addTask(new Task(new TaskName("Test"), new TaskId(123), new TaskStatus(false),
                new AssignedEmployees("")));

        AssignTaskCommand atc = parser.parse("123 1");
        atc.execute(model);

        assertEquals("123", model.getFilteredEmployeeList().get(0).getTasks().getTasks());
    }
}
