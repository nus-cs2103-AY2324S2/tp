package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;

public class AddTaskCommandTest {
    @Test
    public void test() {
        Task validTask = new Task(new TaskName("Test"), new TaskId(123), new TaskStatus(false));
        Model model = new ModelManager();

        AddTaskCommand atc = new AddTaskCommand(validTask);
        AddTaskCommand atc2 = new AddTaskCommand(validTask);
        try {
            atc.execute(model);
        } catch (CommandException e) {
            return;
        }
        assertEquals(123, model.getFilteredTaskList().get(0).getTaskId().taskId);
        assertEquals("Test", model.getFilteredTaskList().get(0).getName().taskName);
        assertEquals(atc.toString(), atc2.toString());
    }
}
