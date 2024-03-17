package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;


public class DeleteTaskCommandTest {
    @Test
    public void test() {
        Task validTask = new Task(new TaskName("Test"), new TaskId(123), new TaskStatus(false));
        Model model = new ModelManager();
        model.addTask(validTask);
        assertTrue(model.getFilteredTaskList().size() == 1);
        DeleteTaskCommand dtc = new DeleteTaskCommand(1);
        try {
            dtc.execute(model);
        } catch (CommandException e) {
            return;
        }
        assertTrue(model.getFilteredTaskList().size() == 1);
        dtc = new DeleteTaskCommand(123);
        try {
            dtc.execute(model);
        } catch (CommandException e) {
            return;
        }
        assertFalse(model.getFilteredTaskList().size() == 1);
    }
}
