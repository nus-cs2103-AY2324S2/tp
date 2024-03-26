package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskStatus;


public class MarkCommandParserTest {
    private MarkCommandParser parser = new MarkCommandParser();
    @Test
    public void test() throws ParseException, CommandException {
        Model model = new ModelManager();
        model.addTask(new Task(new TaskName("Test"), new TaskId(123), new TaskStatus(false)));

        MarkCommand mc = parser.parse("123");
        mc.execute(model);
        assertTrue(model.getFilteredTaskList().get(0).getTaskStatus().toString() == "Completed");
    }
}
