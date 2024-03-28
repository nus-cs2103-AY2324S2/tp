package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SelectExamCommand;

public class SelectExamCommandParserTest {

    private SelectExamCommandParser parser = new SelectExamCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, "1", new SelectExamCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_validArgs_returnsSelectCommand2() {
        assertParseSuccess(parser, "2", new SelectExamCommand(Index.fromOneBased(2)));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectExamCommand.MESSAGE_USAGE);

        // no index provided
        assertParseFailure(parser, "", expectedMessage);

        // non-integer index
        assertParseFailure(parser, "a", expectedMessage);

        // zero index
        assertParseFailure(parser, "0", expectedMessage);

        // negative index
        assertParseFailure(parser, "-1", expectedMessage);
    }
}
