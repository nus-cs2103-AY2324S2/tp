package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.SortCommandParser.MESSAGE_INVALID_COMMAND_LENGTH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validPrefix_returnsSortCommand() {
        assertParseSuccess(parser, "pri/", new SortCommand(0));
        assertParseSuccess(parser, "cn/", new SortCommand(1));
        assertParseSuccess(parser, "n/", new SortCommand(2));
        assertParseSuccess(parser, "tt/", new SortCommand(3));
        assertParseSuccess(parser, "s/", new SortCommand(4));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "a", "Invalid command keyword for sort command");
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "", String.format(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE)));
    }

    @Test
    public void parse_multipleArguments_throwsParseException() {
        assertParseFailure(
                parser, "pri/ com/", String.format(MESSAGE_INVALID_COMMAND_LENGTH, SortCommand.MESSAGE_USAGE));
    }
}
