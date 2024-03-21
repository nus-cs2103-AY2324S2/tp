package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.TaskName;

public class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        String userInput = "addtask n/test 1 d/test 1";
        Task task = new Task(new TaskName("test 1"), new TaskDescription("test 1"));
        AddTaskCommand expectedCommand = new AddTaskCommand(task);
        assertEquals(expectedCommand.getClass(), parser.parse(userInput).getClass());
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        String userInput = "";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
