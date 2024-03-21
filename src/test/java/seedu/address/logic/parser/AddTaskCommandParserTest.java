package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {

    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        String userInput = " " + PREFIX_NAME + "test 1 " + PREFIX_TASK_DESCRIPTION + "test 1";
        Task task = new TaskBuilder().withTaskName("test 1").withTaskDescription("test 1").build();
        assertParseSuccess(parser, userInput, new AddTaskCommand(task));
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        String userInput = "";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
