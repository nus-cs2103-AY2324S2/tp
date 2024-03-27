package seedu.internhub.logic.parser;

import static seedu.internhub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.internhub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhub.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.internhub.logic.commands.FilterCommand;
import seedu.internhub.model.person.MatchingTagPredicate;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "t/     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new MatchingTagPredicate("NR"));
        assertParseSuccess(parser, "t/ NR", expectedFilterCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " t/    NR   ", expectedFilterCommand);
    }
}
