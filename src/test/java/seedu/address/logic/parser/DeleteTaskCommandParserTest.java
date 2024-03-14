package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskId;
import seedu.address.model.task.TaskName;


public class DeleteTaskCommandParserTest {
    private DeleteTaskCommandParser parser = new DeleteTaskCommandParser();
    @Test
    public void test() throws ParseException, CommandException {
        Model m = new ModelManager();
        m.addTask(new Task(new TaskName("Test"), new TaskId(123)));

        DeleteTaskCommand dtc = parser.parse("123");

        assertTrue(m.getFilteredTaskList().size() == 1);
        dtc.execute(m);
        assertTrue(m.getFilteredTaskList().size() == 0);
    }
}
