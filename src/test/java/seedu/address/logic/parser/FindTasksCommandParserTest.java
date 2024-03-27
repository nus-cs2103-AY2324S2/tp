package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;

public class FindTasksCommandParserTest {

    private FindTasksCommandParser parser = new FindTasksCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTasksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTasksCommand() {
        // no leading and trailing whitespaces
        FindTasksCommand expectedFindTasksCommand =
                new FindTasksCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList("project", "meeting")));
        assertParseSuccess(parser, "project meeting", expectedFindTasksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n project \n \t meeting  \t", expectedFindTasksCommand);
    }

}

