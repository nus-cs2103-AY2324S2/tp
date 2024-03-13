package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;

public class AddTaskCommandTest {
    @Test
    public void test() {
        Task validTask = new Task(new TaskName("Test"), new TaskId(123));
        Model model = new ModelManager();

        AddTaskCommand atc = new AddTaskCommand(validTask);
        try {
            atc.execute(model);
        } catch (CommandException e) {
            return;
        }
        assertTrue(model.getFilteredTaskList().get(0).getTaskId().taskId == 123);
        assertTrue(model.getFilteredTaskList().get(0).getName().taskName.equals("Test"));
    }
}
