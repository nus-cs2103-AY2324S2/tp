package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupContainsKeywordsPredicate;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "TUT", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                Group.MESSAGE_CONSTRAINTS_KEYWORD));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new GroupContainsKeywordsPredicate(Arrays.asList("TUT10", "LAB05")));
        assertParseSuccess(parser, "TUT10 LAB05", expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n TUT10 \n \t LAB05  \t", expectedFilterCommand);
    }
}
