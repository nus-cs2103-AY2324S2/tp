package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ReadCommand;

public class ReadCommandParserTest {
    private ReadCommandParser parser = new ReadCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReadCommand.MESSAGE_NOT_READ));
    }

    @Test
    public void parse_validArgs_returnsReadCommand() {
        ReadCommand expectedReadCommand =
                new ReadCommand(ALICE.getNric());
        assertParseSuccess(parser, "T0139571B", expectedReadCommand);
    }

}
