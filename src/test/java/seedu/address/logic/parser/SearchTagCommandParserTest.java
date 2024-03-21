package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SearchTagCommand;
import seedu.address.model.tag.Tag;

public class SearchTagCommandParserTest {
    private SearchCommandParser parser = new SearchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneArg_throwsParseException() {
        assertParseFailure(parser, "2222  3333   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        // no leading and trailing whitespaces
        SearchTagCommand expectedFindCommand =
                new SearchTagCommand(new Tag("friends"));
        assertParseSuccess(parser, "friends", expectedFindCommand);
    }
}
