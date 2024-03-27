package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.AssignedEmployees;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;

public class UnmarkCommandTest {
    @Test
    public void test() throws CommandException {
        Task validTask = new Task(new TaskName("Test"), new TaskId(123), new TaskStatus(false),
                new AssignedEmployees(""));
        Model model = new ModelManager();
        model.addTask(validTask);
        MarkCommand mc = new MarkCommand(123);

        mc.execute(model);

        assertTrue(model.getFilteredTaskList().get(0).getTaskStatus().toString() == "Completed");

        UnmarkCommand umc = new UnmarkCommand(123);

        umc.execute(model);

        assertTrue(model.getFilteredTaskList().get(0).getTaskStatus().toString() == "In Progress");
    }
}
