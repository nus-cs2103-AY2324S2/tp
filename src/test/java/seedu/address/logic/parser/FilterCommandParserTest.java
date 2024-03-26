package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MULTIPLE_TAGS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.tag.Tag;

public class FilterCommandParserTest {
    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        assertParseSuccess(parser, "family", new FilterCommand(new Tag("family")));
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertParseFailure(parser, "family friends", MESSAGE_MULTIPLE_TAGS);
    }

    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "          ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));
    }

}
