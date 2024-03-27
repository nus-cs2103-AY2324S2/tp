package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PinCommand;
import seedu.address.model.person.NusId;



public class PinCommandParserTest {
    private PinCommandParser parser = new PinCommandParser();
    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "E0123456 some random string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "E0123456 i/ string",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArg_returnsPinCommand() {
        NusId testNusId = new NusId("E1234567");
        assertParseSuccess(parser, " id/E1234567", new PinCommand(testNusId));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        // This is invalid because it does not have the NusId prefix
        assertParseFailure(parser, "E1234567",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PinCommand.MESSAGE_USAGE));
    }
}
