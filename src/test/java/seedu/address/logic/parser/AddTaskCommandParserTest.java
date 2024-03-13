package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;


public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();
    @Test
    public void test() throws ParseException, CommandException {
        Model m1 = new ModelManager();
        Model m2 = new ModelManager();
        Task.setUniversalTaskId(5);
        AddTaskCommand atc = parser.parse("testing              ");
        AddTaskCommand atc2 = new AddTaskCommand(new Task(new TaskName("testing"), new TaskId(5)));
        atc.execute(m1);
        atc2.execute(m2);
        assertTrue(m1.equals(m2));
    }
}
