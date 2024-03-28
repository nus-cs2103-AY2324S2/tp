package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CancelCommand;

/**
 * Tests CancelCommandParser for input parsing and validation.
 */
public class CancelCommandParserTest {

    private CancelCommandParser parser = new CancelCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test with non-numeric input, which should fail to parse as an Index
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelCommand.MESSAGE_USAGE));

        // Test with empty string, expecting failure due to no index provided
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelCommand.MESSAGE_USAGE));

        // Test with a numeric value padded with invalid characters, which should also fail
        assertParseFailure(parser, "1 some invalid text",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, CancelCommand.MESSAGE_USAGE));
    }
}
