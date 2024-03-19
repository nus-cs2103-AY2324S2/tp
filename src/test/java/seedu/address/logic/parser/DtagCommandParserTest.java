package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DtagCommand;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class DtagCommandParserTest {

    private DtagCommandParser parser = new DtagCommandParser();

    @Test
    public void parse_validArgs_returnsDtagCommand() {
        assertParseSuccess(parser, "friends", new DtagCommand("friends"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DtagCommand.MESSAGE_USAGE));
    }

}
