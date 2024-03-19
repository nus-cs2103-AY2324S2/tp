package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        assertEquals(1, model.getFilteredTaskList().size());
        DeleteTaskCommand dtc = new DeleteTaskCommand(1);

        assertEquals(dtc.toString(), "seedu.address.logic.commands.DeleteTaskCommand{targetIndex=1}");

        try {
            dtc.execute(model);
        } catch (CommandException e) {
            return;
        }
        assertEquals(1, model.getFilteredTaskList().size());
        dtc = new DeleteTaskCommand(123);
        try {
            dtc.execute(model);
        } catch (CommandException e) {
            return;
        }
        assertNotEquals(1, model.getFilteredTaskList().size());
    }
}
